package com.loansystem.loansystem.controller;

import com.loansystem.loansystem.model.Loan;
import com.loansystem.loansystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("loan")
public class LoanController {
    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("addLoan")
    public Loan addLoan(@RequestParam double balance, @RequestParam Long cin, @RequestParam double interest_rate, @RequestParam Date maturity_date, @RequestParam int pin) {
        return loanService.addLoan(balance, cin, interest_rate, maturity_date, pin);
    }

    @GetMapping("showLoanByCin/{cin}")
    public List<Loan> showLoans(@PathVariable Long cin) {
        return loanService.showLoans(cin);
    }

    @GetMapping("showLoanById/{account_id}")
    public Loan showLoan(@PathVariable Long account_id) {
        return loanService.showLoan(account_id);
    }

    @GetMapping("closeLoan/{account_id}")
    public Loan closeLoan(@PathVariable Long account_id) {
        return loanService.closeLoan(account_id);
    }
}
