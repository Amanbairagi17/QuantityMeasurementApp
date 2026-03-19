package com.app.quantitymeasurement.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		HashMap<String, String> errors = new HashMap<>();
		
		 ex.getBindingResult()
		 	.getFieldErrors()
		 	.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		 
		 log.info(errors.toString());
		 
		 return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		log.info(ex.getMessage());
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
