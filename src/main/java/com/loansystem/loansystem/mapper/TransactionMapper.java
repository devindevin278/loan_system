package com.loansystem.loansystem.mapper;

import com.loansystem.loansystem.model.Transaction;
import com.loansystem.loansystem.modeldto.TransactionDto;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransactionMapper {
    public TransactionDto toDto(Transaction transaction) {
        Double nominal = transaction.getNominal();
        Date created = transaction.getCreated();
        Long loan_id = transaction.getLoan().getAccount_id();
        String transactionType = transaction.getTransactionType().getName();

        return new TransactionDto(nominal, created, loan_id, transactionType);
    }
}
