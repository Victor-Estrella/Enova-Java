package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class SistemasEnergiaTO {
    private Long idEnergia;
    @NotNull private String txTipo;
    @NotNull private String txLocalizacao;
    @NotNull private Double nrCapacidade;
    @NotNull private LocalDate dtInstalacao;
    @NotNull private String stSistema;

    public SistemasEnergiaTO() {
    }

    public SistemasEnergiaTO(Long idEnergia, @NotNull String txTipo, @NotNull String txLocalizacao, @NotNull Double nrCapacidade, @NotNull LocalDate dtInstalacao, @NotNull String stSistema) {
        this.idEnergia = idEnergia;
        this.txTipo = txTipo;
        this.txLocalizacao = txLocalizacao;
        this.nrCapacidade = nrCapacidade;
        this.dtInstalacao = dtInstalacao;
        this.stSistema = stSistema;
    }

    public Long getIdEnergia() {
        return idEnergia;
    }

    public void setIdEnergia(Long idEnergia) {
        this.idEnergia = idEnergia;
    }

    public @NotNull String getTxTipo() {
        return txTipo;
    }

    public void setTxTipo(@NotNull String txTipo) {
        this.txTipo = txTipo;
    }

    public @NotNull String getTxLocalizacao() {
        return txLocalizacao;
    }

    public void setTxLocalizacao(@NotNull String txLocalizacao) {
        this.txLocalizacao = txLocalizacao;
    }

    public @NotNull Double getNrCapacidade() {
        return nrCapacidade;
    }

    public void setNrCapacidade(@NotNull Double nrCapacidade) {
        this.nrCapacidade = nrCapacidade;
    }

    public @NotNull LocalDate getDtInstalacao() {
        return dtInstalacao;
    }

    public void setDtInstalacao(@NotNull LocalDate dtInstalacao) {
        this.dtInstalacao = dtInstalacao;
    }

    public @NotNull String getStSistema() {
        return stSistema;
    }

    public void setStSistema(@NotNull String stSistema) {
        this.stSistema = stSistema;
    }

    public boolean isSistemaAtivo() {
        return "ativo".equalsIgnoreCase(this.stSistema);
    }
}
