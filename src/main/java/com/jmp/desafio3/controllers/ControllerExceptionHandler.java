package com.jmp.desafio3.controllers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jmp.desafio3.dtos.CustonError;
import com.jmp.desafio3.dtos.ValidationError;
import com.jmp.desafio3.exceptions.EntityNotFoundException;
import com.jmp.desafio3.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<CustonError> custonName(
			EntityNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustonError err = new CustonError(
			Instant.now(), 
			status.value(), 
			e.getMessage(), 
			request.getRequestURI()
		);
		
		return ResponseEntity.status(status).body(err);		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustonError> custonName(
			ResourceNotFoundException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustonError err = new CustonError(
			Instant.now(), 
			status.value(), 
			e.getMessage(), 
			request.getRequestURI()
		);
		
		return ResponseEntity.status(status).body(err);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustonError> custonName(
			MethodArgumentNotValidException e, HttpServletRequest request){
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationError err = new ValidationError(
			Instant.now(), 
			404,
			"dados invalidos",
			request.getRequestURI()
		);
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);		
	}
}
