package com.example.demo.exceptions;

import java.io.Serializable;

public class SsnNotFoundException extends Exception implements Serializable {
	 private static final long serialVersionUID = 1L;

	    public SsnNotFoundException() {
	        super();
	    }

	    public SsnNotFoundException(String message) {
	        super(message);
	    }

	    public SsnNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public SsnNotFoundException(Throwable cause) {
	        super(cause);
	    }
	
}
