package br.com.fiap.exceptions;

// Exceção base
public class AnaliseException extends RuntimeException {
  public AnaliseException(String message) {
    super(message);
  }
}