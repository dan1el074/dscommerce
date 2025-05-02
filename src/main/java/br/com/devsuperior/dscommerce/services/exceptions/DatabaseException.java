package br.com.devsuperior.dscommerce.services.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException() {
        super("Falha na integridade referencial!");
    }
}
