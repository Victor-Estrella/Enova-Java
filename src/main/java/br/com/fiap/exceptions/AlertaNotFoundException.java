package br.com.fiap.exceptions;

// Exceção para quando o alerta não é encontrado
public class AlertaNotFoundException extends AppException {
    public AlertaNotFoundException(String message) {
        super(message);
    }
}
