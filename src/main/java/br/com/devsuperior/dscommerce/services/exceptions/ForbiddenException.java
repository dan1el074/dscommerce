package br.com.devsuperior.dscommerce.services.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Access to the resource is prohibited");
    }
}
