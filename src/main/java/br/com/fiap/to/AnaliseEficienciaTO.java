package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class AnaliseEficienciaTO {

    private Long idAnalise;
    private Long idEnergia;
    private LocalDate dtAnalise;
    @NotNull private Double nrProducaoEnergia;
    @NotNull private Double nrConsumoEnergia;
    @NotNull private Double nrEficiencia;

    public AnaliseEficienciaTO() {
    }

    public AnaliseEficienciaTO(Long idAnalise, Long idEnergia, @NotNull LocalDate dtAnalise, @NotNull Double nrProducaoEnergia, @NotNull Double nrConsumoEnergia, @NotNull Double nrEficiencia) {
        this.idAnalise = idAnalise;
        this.idEnergia = idEnergia; // Inicializa idEnergia
        this.dtAnalise = dtAnalise;
        this.nrProducaoEnergia = nrProducaoEnergia;
        this.nrConsumoEnergia = nrConsumoEnergia;
        this.nrEficiencia = nrEficiencia;
    }

    public Long getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(Long idAnalise) {
        this.idAnalise = idAnalise;
    }

    public Long getIdEnergia() {
        return idEnergia;
    }

    public void setIdEnergia(Long idEnergia) {
        this.idEnergia = idEnergia;
    }

    public @NotNull LocalDate getDtAnalise() {
        return dtAnalise;
    }

    public void setDtAnalise(@NotNull LocalDate dtAnalise) {
        this.dtAnalise = dtAnalise;
    }

    public @NotNull Double getNrProducaoEnergia() {
        return nrProducaoEnergia;
    }

    public void setNrProducaoEnergia(@NotNull Double nrProducaoEnergia) {
        this.nrProducaoEnergia = nrProducaoEnergia;
    }

    public @NotNull Double getNrConsumoEnergia() {
        return nrConsumoEnergia;
    }

    public void setNrConsumoEnergia(@NotNull Double nrConsumoEnergia) {
        this.nrConsumoEnergia = nrConsumoEnergia;
    }

    public @NotNull Double getNrEficiencia() {
        return nrEficiencia;
    }

    public void setNrEficiencia(@NotNull Double nrEficiencia) {
        this.nrEficiencia = nrEficiencia;
    }

    public String gerarResumoAnalise() {
        if (nrEficiencia < 50) {
            return "Eficiência baixa: verifique o sistema.";
        } else if (nrEficiencia < 80) {
            return "Eficiência moderada: pode ser otimizada.";
        } else {
            return "Eficiência alta: sistema operando bem.";
        }
    }

}
