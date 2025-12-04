package com.example.demo.adivce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * Global exception handler for Eligibility Determination module.
 * 
 * This class intercepts all unhandled exceptions thrown within the application
 * and returns a clean HTTP response instead of exposing stack traces.
 * 
 * - Catches any Exception type.
 * - Returns the exception message with HTTP 200 (OK) to avoid breaking client flows.
 */
@RestControllerAdvice
public class PlanEligibilityExceptionAdvice {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);

	}

}
