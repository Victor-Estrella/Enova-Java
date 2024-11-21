package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UsuariosTO {
    private Long idUsuario;
    @NotNull
    private String nmUsuario;
    @NotNull private String txEmail;
    @NotNull private String txSenha;
    private LocalDate dtCadastro;

    public UsuariosTO() {
    }

    public UsuariosTO(Long idUsuario, @NotNull String nmUsuario, @NotNull String txEmail, @NotNull String txSenha, LocalDate dtCadastro) {
        this.idUsuario = idUsuario;
        this.nmUsuario = nmUsuario;
        this.txEmail = txEmail;
        this.txSenha = txSenha;
        this.dtCadastro = dtCadastro;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public @NotNull String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(@NotNull String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public @NotNull String getTxEmail() {
        return txEmail;
    }

    public void setTxEmail(@NotNull String txEmail) {
        this.txEmail = txEmail;
    }

    public @NotNull String getTxSenha() {
        return txSenha;
    }

    public void setTxSenha(@NotNull String txSenha) {
        this.txSenha = txSenha;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public boolean isEmailValido() {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.compile(emailRegex).matcher(this.txEmail).matches();
    }
}
