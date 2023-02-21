package com.xpand.challenge.exception;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<RestExceptionResponse> handleNotFound(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RestExceptionResponse(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<RestExceptionResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new RestExceptionResponse(e.getMessage()));
    }
}
