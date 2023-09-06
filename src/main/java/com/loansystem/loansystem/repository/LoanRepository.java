package com.loansystem.loansystem.repository;

import com.loansystem.loansystem.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByCin(Long cin);
}
