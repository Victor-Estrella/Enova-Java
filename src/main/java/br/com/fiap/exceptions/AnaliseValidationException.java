// Exceção para validações falhas
package br.com.fiap.exceptions;

public class AnaliseValidationException extends AnaliseException {
  public AnaliseValidationException(String message) {
    super(message);
  }
}