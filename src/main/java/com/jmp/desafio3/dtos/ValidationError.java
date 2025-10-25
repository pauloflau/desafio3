package com.jmp.desafio3.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustonError{
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(Instant ValidationError, Integer status, String error, String path) {
		super(ValidationError, status, error, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
}
