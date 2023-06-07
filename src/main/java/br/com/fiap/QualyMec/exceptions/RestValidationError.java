package br.com.fiap.QualyMec.exceptions;

public record RestValidationError(String field, String message) {
    
}
