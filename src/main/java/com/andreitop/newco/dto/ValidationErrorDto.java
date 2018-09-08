package com.andreitop.newco.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto {

    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public ValidationErrorDto() {
    }

    public void addFieldError(String path, String message) {
        FieldErrorDto error = new FieldErrorDto(path, message);
        fieldErrors.add(error);
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}