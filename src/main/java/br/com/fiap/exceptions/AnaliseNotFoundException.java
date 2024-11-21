// Exceção para quando a análise não é encontrada
package br.com.fiap.exceptions;

public class AnaliseNotFoundException extends AnaliseException {
  public AnaliseNotFoundException(String message) {
    super(message);
  }
}