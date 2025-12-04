package com.example.demo.adivce;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PlanEligibilityExceptionAdvice {
	  // This method handles all exceptions thrown by any controller
    // Returns the exception message in the HTTP response body with status 200 OK
    @ExceptionHandler(Exception.class)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllExceptions(Exception e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);

	}

}
