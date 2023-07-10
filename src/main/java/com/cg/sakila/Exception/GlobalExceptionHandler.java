package com.cg.sakila.Exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.AnnotationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// custom exception
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<?> handleCustomException(CustomException ex) {
		Error errRes = new Error(ex.getMessage());
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}

	// constraint violation handler
	@ExceptionHandler(AnnotationException.class)
	public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
		Error errRes = new Error("Enter correct information...");
		return new ResponseEntity<>(errRes, HttpStatus.BAD_REQUEST);
	}
	
	// method argument not valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> validationErrs = new ArrayList<>();
		for (FieldError err : ex.getBindingResult().getFieldErrors())
			validationErrs.add(err.getDefaultMessage());

		Error errResp = new Error("Validation Failed...");
		return new ResponseEntity<Object>(errResp, status);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(
			SQLIntegrityConstraintViolationException ex) {
		Error errorResponse = new Error("Mandatory fields required...");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
