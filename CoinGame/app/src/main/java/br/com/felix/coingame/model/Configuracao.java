package br.com.felix.coingame.model;

public class Configuracao {

    private Long id;
    private Boolean tocarMusica;
    private Boolean tocarSom;
    private Integer quantidadeJogada;
    private Integer quantidadePontosSeguidos;
    private Integer quantidadeAcerto;
    private Integer quantidadeErro;
    private Integer quantidadeCaras;
    private Integer quantidadeCoroas;

    public Configuracao(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTocarMusica() {
        return tocarMusica;
    }

    public void setTocarMusica(Boolean tocarMusica) {
        this.tocarMusica = tocarMusica;
    }

    public Boolean getTocarSom() {
        return tocarSom;
    }

    public void setTocarSom(Boolean tocarSom) {
        this.tocarSom = tocarSom;
    }

    public Integer getQuantidadeJogada() {
        return quantidadeJogada;
    }

    public void setQuantidadeJogada(Integer quantidadeJogada) {
        this.quantidadeJogada = quantidadeJogada;
    }

    public Integer getQuantidadePontosSeguidos() {
        return quantidadePontosSeguidos;
    }

    public void setQuantidadePontosSeguidos(Integer quantidadePontosSeguidos) {
        this.quantidadePontosSeguidos = quantidadePontosSeguidos;
    }

    public Integer getQuantidadeAcerto() {
        return quantidadeAcerto;
    }

    public void setQuantidadeAcerto(Integer quantidadeAcerto) {
        this.quantidadeAcerto = quantidadeAcerto;
    }

    public Integer getQuantidadeErro() {
        return quantidadeErro;
    }

    public void setQuantidadeErro(Integer quantidadeErro) {
        this.quantidadeErro = quantidadeErro;
    }

    public Integer getQuantidadeCaras() {
        return quantidadeCaras;
    }

    public void setQuantidadeCaras(Integer quantidadeCaras) {
        this.quantidadeCaras = quantidadeCaras;
    }

    public Integer getQuantidadeCoroas() {
        return quantidadeCoroas;
    }

    public void setQuantidadeCoroas(Integer quantidadeCoroas) {
        this.quantidadeCoroas = quantidadeCoroas;
    }
}
