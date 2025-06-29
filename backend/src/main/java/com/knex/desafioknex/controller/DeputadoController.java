package com.knex.desafioknex.controller;
import java.math.BigDecimal;
import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.knex.desafioknex.dto.DeputadoDTO;
import com.knex.desafioknex.dto.DespesaDTO;
import com.knex.desafioknex.service.DeputadoService;
import org.springframework.http.MediaType;


@RestController
@RequestMapping ("/api/deputado")
public class DeputadoController {

    private final DeputadoService deputadoService;
   

    @Autowired
    public DeputadoController(DeputadoService deputadoService) {
        this.deputadoService = deputadoService;
    }


    @PostMapping (value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file) {
        try {
            deputadoService.uploadCSV(file);
            return ResponseEntity.ok("Arquivo CSV processado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo CSV: " + e.getMessage());
        }
    }


    @GetMapping ("/findByUf")
    public List<DeputadoDTO> listarDeputadosPorUf(@RequestParam String uf) {
        return deputadoService.buscarDeputadosPorUf(uf);
    }


    @GetMapping (value = "deputados/{id}/total-expenses")
    public BigDecimal somatorioPorDeputado(@PathVariable Long id){
        return deputadoService.somatorioPorDeputado(id);
    }



    @GetMapping ("/sum-total-expenses")
    public BigDecimal somatorioTotalDespesas() {
        return deputadoService.somatorioTotalDespesas();
    }



    @GetMapping("/deputados/{id}/despesas")
    public List<DespesaDTO> listarDespesasPorDeputado(@PathVariable Long id) {
        return deputadoService.listarDespesasPorDeputado(id);
    }
    
}
