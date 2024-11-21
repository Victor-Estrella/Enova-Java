package br.com.fiap.exceptions;

public class UsuarioEmailInvalidException extends RuntimeException {
  public UsuarioEmailInvalidException(String message) {
    super(message);
  }
}
