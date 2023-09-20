package com.loansystem.loansystem.mapper;

import com.loansystem.loansystem.model.Loan;
import com.loansystem.loansystem.modeldto.LoanDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoanMapper {
    public LoanDto toDto(Loan loan) {
        Long account_id = loan.getAccount_id();
        Long cin = loan.getCin();
        Double interestRate = loan.getInterestRate();
        Double balance = loan.getBalance();
        Double interestAmount = loan.getInterestAmount();
        Date issueDate = loan.getIssueDate();
        Date maturityDate = loan.getMaturityDate();
        String status = loan.getStatus().getName();
        return new LoanDto(account_id, cin, interestRate, balance, interestAmount, issueDate, maturityDate, status);
    }
}
