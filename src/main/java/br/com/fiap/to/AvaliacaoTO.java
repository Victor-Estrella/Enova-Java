package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

public class AvaliacaoTO {
    private Long idAvaliacao;
    @NotNull private String nmUsuario;
    @NotNull private String txEmail;
    @NotNull private String txMensagem;
    @NotNull private int nrAvaliacao;

    public AvaliacaoTO() {
    }

    public AvaliacaoTO(Long idAvaliacao, @NotNull String nmUsuario, @NotNull String txEmail, @NotNull String txMensagem, @NotNull int nrAvaliacao) {
        this.idAvaliacao = idAvaliacao;
        this.nmUsuario = nmUsuario;
        this.txEmail = txEmail;
        this.txMensagem = txMensagem;
        this.nrAvaliacao = nrAvaliacao;
    }

    @NotNull
    public int getNrAvaliacao() {
        return nrAvaliacao;
    }

    public void setNrAvaliacao(@NotNull int nrAvaliacao) {
        this.nrAvaliacao = nrAvaliacao;
    }

    public @NotNull String getTxMensagem() {
        return txMensagem;
    }

    public void setTxMensagem(@NotNull String txMensagem) {
        this.txMensagem = txMensagem;
    }

    public @NotNull String getTxEmail() {
        return txEmail;
    }

    public void setTxEmail(@NotNull String txEmail) {
        this.txEmail = txEmail;
    }

    public @NotNull String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(@NotNull String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public Long getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public boolean isAvaliacaoValida() {
        return this.nrAvaliacao >= 1 && this.nrAvaliacao <= 5;
    }

}
