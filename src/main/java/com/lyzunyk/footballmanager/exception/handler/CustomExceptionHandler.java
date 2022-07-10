package com.lyzunyk.footballmanager.exception.handler;

import com.lyzunyk.footballmanager.exception.ExceptionResponse;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.exception.ProcessPaymentException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotExistException.class)
    public final ResponseEntity<Object> handleNotExistException(NotExistException exception) {
        return buildExceptionBody(exception, NOT_FOUND);
    }

    @ExceptionHandler(ProcessPaymentException.class)
    public final ResponseEntity<Object> handleProcessPaymentException(ProcessPaymentException exception) {
        return buildExceptionBody(exception, BAD_REQUEST);
    }

    private ResponseEntity<Object> buildExceptionBody(Exception exception, HttpStatus httpStatus) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .status(httpStatus.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .status(httpStatus)
                .body(exceptionResponse);
    }
}
