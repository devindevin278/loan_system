package com.loansystem.loansystem.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    private Long cin;
    private Integer pin;



    private Double interestRate;
    private Double balance;
    private Double interestAmount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @PrePersist
    protected void onCreate() {
        issueDate = new Date();
    }
    private Date maturityDate;

    @OneToMany(mappedBy = "loan")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;


    public Loan() {
    }

    public Loan(Long cin, Integer pin, Double interestRate, Double balance, Double interestAmount, Date issueDate, Date maturityDate, Status status) {
        this.cin = cin;
        this.pin = pin;
        this.interestRate = interestRate;
        this.balance = balance;
        this.interestAmount = interestAmount;
        this.issueDate = issueDate;
        this.maturityDate = maturityDate;
        this.status = status;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
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

    @Override
    public String toString() {
        return "Loan{" +
                "account_id=" + account_id +
                ", cin=" + cin +
                ", interestRate=" + interestRate +
                ", balance=" + balance +
                ", interestAmount=" + interestAmount +
                ", issueDate=" + issueDate +
                ", maturityDate=" + maturityDate +
                '}';
    }
}
