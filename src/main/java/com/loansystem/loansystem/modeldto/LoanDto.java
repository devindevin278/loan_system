package com.loansystem.loansystem.modeldto;

import com.loansystem.loansystem.model.Status;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

public class LoanDto {
    private Long account_id;
    private Long cin;
    private Double interestRate;
    private Double balance;
    private Double interestAmount;
    private Date issueDate;
    private Date maturityDate;
    private String status;

    public LoanDto() {
    }

    public LoanDto(Long account_id, Long cin, Double interestRate, Double balance, Double interestAmount, Date issueDate, Date maturityDate, String status) {
        this.account_id = account_id;
        this.cin = cin;
        this.interestRate = interestRate;
        this.balance = balance;
        this.interestAmount = interestAmount;
        this.issueDate = issueDate;
        this.maturityDate = maturityDate;
        this.status = status;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(Double interestAmount) {
        this.interestAmount = interestAmount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(Date maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
