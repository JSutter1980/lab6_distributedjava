package edu.wctc.stockpurchase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(
        ResourceNotFoundException exception,
        ServletWebRequest request) {


        ExceptionResponse responseObject = new ExceptionResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getRequest().getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObject);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleBadArgumentException(
            MethodArgumentTypeMismatchException exception,
            ServletWebRequest request) {

        ExceptionResponse responseObject = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequest().getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
    }

    @ExceptionHandler(InvalidRequest.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequest(
            InvalidRequest exception,
            ServletWebRequest request) {

        ExceptionResponse responseObject = new ExceptionResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequest().getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObject);
    }
}
