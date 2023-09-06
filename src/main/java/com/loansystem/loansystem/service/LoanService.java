package com.loansystem.loansystem.service;

import com.loansystem.loansystem.model.Loan;
import com.loansystem.loansystem.model.Status;
import com.loansystem.loansystem.repository.LoanRepository;
import com.loansystem.loansystem.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Stack;

@Service
public class LoanService {

    private LoanRepository loanRepository;
    private StatusRepository statusRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public LoanService(LoanRepository loanRepository, StatusRepository statusRepository, RestTemplate restTemplate) {
        this.loanRepository = loanRepository;
        this.statusRepository = statusRepository;
        this.restTemplate = restTemplate;
    }

    public Loan addLoan(double balance, Long cin, double interest_rate, Date maturity_date, int pin) {
//      save ke loan system
        Status status = statusRepository.findById((long)1).orElse(null);
        Loan loan = new Loan(cin, pin, interest_rate, balance, (double)0, null, maturity_date, status);
        Loan newLoan = loanRepository.save(loan);

//      ambil account_id baru
        Long account_id = newLoan.getAccount_id();

//      save ke customer system
        String apiUrl = "http://localhost:8082/product/addProduct";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("cin", cin)
                .queryParam("account_id", account_id)
                .queryParam("product_id", 2);

        String finalUrl = builder.toUriString();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(finalUrl, null, Void.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return newLoan;
        } else {
            throw new RuntimeException("Failed to create loan");
        }
    }

    public List<Loan> showLoan(Long cin) {
        return loanRepository.findByCin(cin);
    }

    public Loan closeLoan(Long account_id) {

        Loan loan = loanRepository.findById(account_id).orElse(null);

        Status closed = statusRepository.findById((long)2).orElse(null);
        loan.setStatus(closed);

        loanRepository.save(loan);

//        save ke customer system
        String apiUrl = "http://localhost:8082/product/closeProduct";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("account_id", account_id)
                .queryParam("product_id", 2);

        String finalUrl = builder.toUriString();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(finalUrl, null, Void.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return loan;
        } else {
            throw new RuntimeException("Failed to close loan");
        }
    }

}
