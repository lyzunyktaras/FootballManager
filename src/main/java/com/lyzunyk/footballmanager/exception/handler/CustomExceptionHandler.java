package com.lyzunyk.footballmanager.exception.handler;

import com.lyzunyk.footballmanager.exception.ExceptionResponse;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.exception.ProcessPaymentException;
import com.lyzunyk.footballmanager.exception.ProcessTransferException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(ProcessTransferException.class)
    public final ResponseEntity<Object> handleProcessTransferException(ProcessTransferException exception) {
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }
}
