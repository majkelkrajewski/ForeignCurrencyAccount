package com.sda.ForeignCurrencyAccount.config;

import com.sda.ForeignCurrencyAccount.shared.exceptions.BadRequestException;
import com.sda.ForeignCurrencyAccount.shared.exceptions.ResourceAlreadyExistsException;
import com.sda.ForeignCurrencyAccount.shared.exceptions.ResourceNotExistsException;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleException(BadRequestException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(ResourceNotExistsException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @Value
    class ErrorResponse {
        String msg;
    }
}
