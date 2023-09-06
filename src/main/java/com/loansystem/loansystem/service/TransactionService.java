package com.loansystem.loansystem.service;

import com.loansystem.loansystem.controller.TransactionController;
import com.loansystem.loansystem.model.Loan;
import com.loansystem.loansystem.model.Status;
import com.loansystem.loansystem.model.Transaction;
import com.loansystem.loansystem.model.TransactionType;
import com.loansystem.loansystem.repository.LoanRepository;
import com.loansystem.loansystem.repository.StatusRepository;
import com.loansystem.loansystem.repository.TransactionRepository;
import com.loansystem.loansystem.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionTypeRepository transactionTypeRepository;
    private LoanRepository loanRepository;
    private RestTemplate restTemplate;
    private StatusRepository statusRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionTypeRepository transactionTypeRepository, LoanRepository loanRepository, RestTemplate restTemplate, StatusRepository statusRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.loanRepository = loanRepository;
        this.restTemplate = restTemplate;
        this.statusRepository = statusRepository;
    }

    public List<Transaction> showTransaction(Long loan_id) {
        return transactionRepository.findAllByLoan(loan_id);
    }

    public List<Transaction> showTransactionByDate(Long loan_id, Date startDate, Date finishDate) {
        return transactionRepository.findAllByDate(loan_id, startDate, finishDate);
    }

    public Transaction repayment(double nominal, long account_id) {
        Loan loan = loanRepository.findById(account_id).orElse(null);
        double interestAmount = loan.getInterestAmount();
        double balance = loan.getBalance();
        double dueAmount = interestAmount + balance;

        // full repayment
        if(nominal == dueAmount) {
//           change loan detail
            loan.setBalance((double)0);
            loan.setInterestAmount((double)0);
            Status closed = statusRepository.findById((long)2).orElse(null);
            loan.setStatus(closed);

            loanRepository.save(loan);

//          update status to closed in customer system
            String apiUrl = "http://localhost:8082/product/closeProduct";

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("account_id", account_id)
                    .queryParam("product_id", 2);

            String finalUrl = builder.toUriString();

            ResponseEntity<Void> responseEntity = restTemplate.postForEntity(finalUrl, null, Void.class);

            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to close deposit");
            }


        } else if (nominal < dueAmount) { // partial repayment

            // pay interest amount first
            interestAmount = interestAmount - nominal;
            if(interestAmount < 0) {
                balance = balance + interestAmount;
                interestAmount = 0;
            }

//            change loan detail
            loan.setBalance(balance);
            loan.setInterestAmount(interestAmount);

            loanRepository.save(loan);
        }

//        save transaction
        TransactionType transactionType = transactionTypeRepository.findById((long)2).orElse(null);

        Transaction transaction = new Transaction(nominal, null, loan, transactionType);

        return transactionRepository.save(transaction);
    }


}
