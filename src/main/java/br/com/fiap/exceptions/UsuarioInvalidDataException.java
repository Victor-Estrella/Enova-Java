package br.com.fiap.exceptions;

public class UsuarioInvalidDataException extends RuntimeException {
  public UsuarioInvalidDataException(String message) {
    super(message);
  }
}
