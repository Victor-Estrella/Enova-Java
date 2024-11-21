package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ManutencaoTO {

    @NotNull private Long idManutencao;
    private Long idEnergia;
    @NotNull private String dsManutencao;
    @NotNull private LocalDate dtManutencao;
    @NotNull private String tpManutencao;

    public ManutencaoTO() {
    }

    public ManutencaoTO(Long idManutencao, Long idEnergia, @NotNull String dsManutencao, @NotNull LocalDate dtManutencao, @NotNull String tpManutencao) {
        this.idManutencao = idManutencao;
        this.idEnergia = idEnergia; // Inicializa idEnergia
        this.dsManutencao = dsManutencao;
        this.dtManutencao = dtManutencao;
        this.tpManutencao = tpManutencao;
    }

    public @NotNull Long getIdManutencao() {
        return idManutencao;
    }

    public void setIdManutencao(@NotNull Long idManutencao) {
        this.idManutencao = idManutencao;
    }

    public Long getIdEnergia() {
        return idEnergia;
    }

    public void setIdEnergia(Long idEnergia) {
        this.idEnergia = idEnergia;
    }

    public @NotNull String getDsManutencao() {
        return dsManutencao;
    }

    public void setDsManutencao(@NotNull String dsManutencao) {
        this.dsManutencao = dsManutencao;
    }

    public @NotNull LocalDate getDtManutencao() {
        return dtManutencao;
    }

    public void setDtManutencao(@NotNull LocalDate dtManutencao) {
        this.dtManutencao = dtManutencao;
    }

    public @NotNull String getTpManutencao() {
        return tpManutencao;
    }

    public void setTpManutencao(@NotNull String tpManutencao) {
        this.tpManutencao = tpManutencao;
    }
}
