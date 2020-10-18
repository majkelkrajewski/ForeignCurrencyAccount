package com.sda.ForeignCurrencyAccount.shared.exceptions;

public class ResourceNotExistsException extends RuntimeException{
    public ResourceNotExistsException(String message) {
        super(message);
    }
}
