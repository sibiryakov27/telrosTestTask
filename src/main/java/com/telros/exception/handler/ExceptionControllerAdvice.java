package com.telros.exception.handler;

import com.telros.exception.BaseException;
import com.telros.exception.response.BaseExceptionResponse;
import com.telros.exception.response.ValidationErrorResponse;
import com.telros.exception.response.Violation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseExceptionResponse> handleBaseException(BaseException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(BaseExceptionResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleVConstraintViolationException(
            ConstraintViolationException ex
    ) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            validationErrorResponse.getViolations().add(
                    new Violation(violation.getPropertyPath().toString(), violation.getMessage())
            );
        }
        return ResponseEntity
                .badRequest()
                .body(validationErrorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            validationErrorResponse.getViolations().add(
                    new Violation(fieldError.getField(), fieldError.getDefaultMessage())
            );
        }
        return ResponseEntity
                .badRequest()
                .body(validationErrorResponse);
    }
}
