package com.tcs.exception;

@SuppressWarnings("serial")
public class DuplicateLoanApplicationException extends RuntimeException {
    public DuplicateLoanApplicationException(String message) {
        super(message);
    }
}