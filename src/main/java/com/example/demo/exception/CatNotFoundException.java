package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No cat found with that id")
public class CatNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2198257676115661200L;

	// Checked exception must be handled

}
