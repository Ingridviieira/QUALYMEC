package br.com.fiap.QualyaMec.exceptions;

public record RestValidationError(String field, String message) {
    
}
