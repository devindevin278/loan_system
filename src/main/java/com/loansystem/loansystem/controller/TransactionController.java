package com.loansystem.loansystem.controller;

import com.loansystem.loansystem.model.Transaction;
import com.loansystem.loansystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("showTransaction")
    public List<Transaction> showTransaction(@RequestParam Long loan_id) {
        return transactionService.showTransaction(loan_id);
    }

    @PostMapping("showTransaction/filterByDate")
    public List<Transaction> showTransaction(@RequestParam Long loan_id, Date startDate, Date finishDate) {
        return transactionService.showTransactionByDate(loan_id, startDate, finishDate);
    }
    // date = "mm/dd/yyyy"

    @PostMapping("repayment")
    public Transaction repayment(@RequestParam double nominal, @RequestParam long account_id) {
        return transactionService.repayment(nominal, account_id);
    }


}
