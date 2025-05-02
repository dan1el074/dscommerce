package br.com.devsuperior.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }
}
