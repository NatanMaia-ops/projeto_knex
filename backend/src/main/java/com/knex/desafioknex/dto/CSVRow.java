package com.knex.desafioknex.dto;
import com.opencsv.bean.CsvBindByPosition;

public class CSVRow {

    @CsvBindByPosition(position = 0)
    private String nomeParlamentar;

    @CsvBindByPosition(position = 1)
    private String cpf;

    @CsvBindByPosition(position = 5)
    private String uf;

    @CsvBindByPosition(position = 6)
    private String partido;

    @CsvBindByPosition(position = 12)
    private String fornecedor;

    @CsvBindByPosition(position = 19)
    private Float valorLiquido;

    @CsvBindByPosition(position = 31)
    private String url;

    @CsvBindByPosition(position = 16)
    private String dataEmissao;

    public String getNomeParlamentar() {
        return nomeParlamentar;
    }

    public void setNomeParlamentar(String nomeParlamentar) {
        this.nomeParlamentar = nomeParlamentar;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Float getValorLiquido() {
        return valorLiquido;
    }

    public void setValorLiquido(Float valorLiquido) {
        this.valorLiquido = valorLiquido;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
