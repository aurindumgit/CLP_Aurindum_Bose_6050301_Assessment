package com.tcs.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidLoanAmountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidAmount(InvalidLoanAmountException ex) {
        return new ErrorResponse(
                "InvalidLoanAmountException",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(DuplicateLoanApplicationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDuplicate(DuplicateLoanApplicationException ex) {
        return new ErrorResponse(
                "DuplicateLoanApplicationException",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(LoanNotFoundException ex) {
        return new ErrorResponse(
                "LoanNotFoundException",
                ex.getMessage(),
                LocalDateTime.now()
        );
    }
}