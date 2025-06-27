package com.knex.desafioknex.service;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.knex.desafioknex.dto.CSVRow;
import com.knex.desafioknex.dto.DeputadoDTO;
import com.knex.desafioknex.dto.DespesaDTO;
import com.knex.desafioknex.model.Deputado;
import com.knex.desafioknex.model.Despesa;
import com.knex.desafioknex.repository.DeputadoRepository;
import com.knex.desafioknex.repository.DespesaRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
public class DeputadoService {

    private final DeputadoRepository deputadoRepo;
    private final DespesaRepository despesaRepo;

    public DeputadoService(DeputadoRepository deputadoRepo, DespesaRepository despesaRepo) {
        this.deputadoRepo = deputadoRepo;
        this.despesaRepo = despesaRepo;
    }

    public void uploadCSV(MultipartFile file) throws Exception {
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<CSVRow> csvToBean = new CsvToBeanBuilder<CSVRow>(reader)
                    .withType(CSVRow.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSkipLines(1)
                    .build();

            int linha = 1;
            for (CSVRow row : csvToBean) {
                if (linha > 3000) {
                    System.out.println("Limite de 5000 linhas atingido. Parando processamento.");
                    break;
                }

                String nome = row.getNomeParlamentar() != null ? row.getNomeParlamentar().trim() : "";
                String cpf = row.getCpf() != null ? row.getCpf().trim() : "";
                String partido = row.getPartido() != null ? row.getPartido().trim() : "";
                String uf = row.getUf() != null ? row.getUf().trim() : "";
                String url = row.getUrl() != null ? row.getUrl().trim() : "";
                String fornecedor = row.getFornecedor() != null ? row.getFornecedor().trim() : "";
                Float valorLiquidoFloat = row.getValorLiquido();
                BigDecimal valorLiquido = valorLiquidoFloat != null ? BigDecimal.valueOf(valorLiquidoFloat) : BigDecimal.ZERO;
                String dataStr = row.getDataEmissao() != null ? row.getDataEmissao().trim() : "";

                System.out.println("\n=== [Linha " + linha + "] ===");
                System.out.println("Nome: " + nome + " | CPF: " + cpf + " | UF: " + uf + " | Partido: " + partido);

            
                if (nome.isEmpty() || nome.toUpperCase().contains("LID.")
                        || cpf.isEmpty() || url.isEmpty()) {
                    System.out.println("Linha " + linha + " ignorada: campos obrigatórios ausentes ou inválidos.");
                    linha++;
                    continue;
                }

                // Deputado
                Deputado deputado = deputadoRepo.findByCpf(cpf)
                        .orElseGet(() -> {
                            Deputado novo = new Deputado();
                            novo.setCpf(cpf);
                            novo.setNome(nome);
                            novo.setPartido(partido);
                            novo.setUf(uf);
                            System.out.println("Novo deputado salvo: " + novo);
                            return deputadoRepo.save(novo);
                        });

                // Despesa
                Despesa despesa = new Despesa();
                despesa.setDeputado(deputado);
                despesa.setUrl(url);
                despesa.setFornecedor(fornecedor);
                despesa.setValorLiquido(valorLiquido);

                // Conversão de data
                if (!dataStr.isEmpty() && !dataStr.equalsIgnoreCase("text")) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                        LocalDateTime dataEmissao = LocalDateTime.parse(dataStr, formatter);
                        despesa.setDataEmissao(dataEmissao);
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida na linha " + linha + ": " + dataStr);
                        linha++;
                        continue;
                    }
                }

                try {
                    despesaRepo.save(despesa);
                    System.out.println("Despesa salva com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro ao salvar despesa na linha " + linha + ": " + e.getMessage());
                    e.printStackTrace();
                }

                linha++;
            }
        }
    }

    public List<DeputadoDTO> buscarDeputadosPorUf(String uf) {
        List<Deputado> deputados = deputadoRepo.findByUfIgnoreCase(uf);
        return deputados.stream()
                .map(dep -> new DeputadoDTO(
                        dep.getId(),
                        dep.getNome(),
                        dep.getCpf(),
                        dep.getPartido(),
                        dep.getUf()
                ))
                .collect(Collectors.toList());
    }

    public BigDecimal somatorioPorDeputado(Long deputadoId){
        BigDecimal total = despesaRepo.somatorioPorDeputado(deputadoId);
        return total != null ? total : BigDecimal.ZERO;
    }

    public BigDecimal somatorioTotalDespesas(){
        BigDecimal total = despesaRepo.somatorioTotalDespesas();
        return total != null ? total : BigDecimal.ZERO; 
    }

    
    public List<DespesaDTO> listarDespesasPorDeputado(Long id) {
        return despesaRepo.findByDeputadoId(id)
                .stream()
                .map(despesa -> new DespesaDTO(
                        despesa.getId(),
                        despesa.getDataEmissao(),
                        despesa.getFornecedor(),
                        despesa.getValorLiquido().floatValue(),
                        despesa.getUrl()
                ))
                .collect(Collectors.toList());
    }

}
