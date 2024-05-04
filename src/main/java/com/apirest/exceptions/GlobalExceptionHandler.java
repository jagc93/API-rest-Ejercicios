package com.apirest.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler({ NullPointerException.class, IllegalArgumentException.class, Exception.class })
	public ResponseEntity<String> handlePropertyReferenceException(Throwable t) {
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(t.getMessage());
	}
}
