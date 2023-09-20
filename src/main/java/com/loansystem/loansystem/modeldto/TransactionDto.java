package com.loansystem.loansystem.modeldto;

import java.util.Date;

public class TransactionDto {
    private Double nominal;
    private Date created;
    private Long loan_id;
    private String transactionType;

    public TransactionDto() {
    }

    public TransactionDto(Double nominal, Date created, Long loan_id, String transactionType) {
        this.nominal = nominal;
        this.created = created;
        this.loan_id = loan_id;
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getNominal() {
        return nominal;
    }

    public void setNominal(Double nominal) {
        this.nominal = nominal;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }
}
