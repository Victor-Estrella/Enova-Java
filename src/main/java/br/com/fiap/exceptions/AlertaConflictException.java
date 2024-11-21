package br.com.fiap.exceptions;

// Exceção para conflitos, como duplicação de códigos
public class AlertaConflictException extends AppException {
    public AlertaConflictException(String message) {
        super(message);
    }
}
