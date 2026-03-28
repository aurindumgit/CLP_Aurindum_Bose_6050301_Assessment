package com.tcs.exception;

@SuppressWarnings("serial")
public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(String message) {
        super(message);
    }
}