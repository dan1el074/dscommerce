package br.com.devsuperior.dscommerce.dto.errors;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidError extends CustomError{
    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(FieldMessage fieldMessage) {
        errors.add(fieldMessage);
    }
}
