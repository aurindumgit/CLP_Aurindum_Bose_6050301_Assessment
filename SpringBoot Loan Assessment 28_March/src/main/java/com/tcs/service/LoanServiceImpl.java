package com.tcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.entity.Loan;
import com.tcs.exception.*;
import com.tcs.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repository;

    @Override
    public Loan createLoan(Loan loan) {

        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException(
                    "Loan amount must be between 1 and 5000000"
            );
        }

        repository.findByApplicantNameAndStatus(
                loan.getApplicantName(), "PENDING"
        ).ifPresent(l -> {
            throw new DuplicateLoanApplicationException(
                    "User already has a pending loan"
            );
        });

        loan.setStatus("PENDING");
        return repository.save(loan);
    }

    @Override
    public List<Loan> getAllLoans() {
        return repository.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with ID: " + id));
    }

    @Override
    public Loan updateLoanStatus(Long id, String status) {

        Loan loan = getLoanById(id);

        if (!status.equalsIgnoreCase("APPROVED") &&
            !status.equalsIgnoreCase("REJECTED")) {
            throw new RuntimeException("Invalid status value");
        }

        loan.setStatus(status.toUpperCase());
        return repository.save(loan);
    }
}