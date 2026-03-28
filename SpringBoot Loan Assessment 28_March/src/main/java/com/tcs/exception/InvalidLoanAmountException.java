package com.tcs.exception;

@SuppressWarnings("serial")
public class InvalidLoanAmountException extends RuntimeException {
    public InvalidLoanAmountException(String message) {
        super(message);
    }
}