package br.com.fiap.exceptions;

// Exceção para validações específicas
public class AlertaValidationException extends AppException {
    public AlertaValidationException(String message) {
        super(message);
    }
}
