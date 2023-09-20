package com.loansystem.loansystem.controller;

import com.loansystem.loansystem.mapper.LoanMapper;
import com.loansystem.loansystem.model.Loan;
import com.loansystem.loansystem.modeldto.LoanDto;
import com.loansystem.loansystem.modeldto.StatusMessageDto;
import com.loansystem.loansystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("loan")
public class LoanController {
    private LoanService loanService;
    private LoanMapper loanMapper;

    @Autowired
    public LoanController(LoanService loanService, LoanMapper loanMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @PostMapping("addLoan")
    public ResponseEntity<?> addLoan(@RequestParam double balance, @RequestParam Long cin, @RequestParam double interest_rate, @RequestParam Date maturity_date, @RequestParam int pin) {
        StatusMessageDto<LoanDto> responseMsg = new StatusMessageDto<LoanDto>();
        try {
            Loan loan = loanService.addLoan(balance, cin, interest_rate, maturity_date, pin);
            LoanDto depositDto = loanMapper.toDto(loan);

            responseMsg.setStatus(HttpStatus.CREATED.value());
            responseMsg.setMessage("Loan added successfully");
            responseMsg.setData(depositDto);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseMsg);
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("showLoanByCin")
    public ResponseEntity<?> showLoans(@RequestParam Long cin) {
        StatusMessageDto<List<LoanDto>> responseMsg = new StatusMessageDto<List<LoanDto>>();
        try {
            List<Loan> loans = loanService.showLoans(cin);
            List<LoanDto> loanDtos = new ArrayList<LoanDto>();
            for(Loan item: loans) {
                loanDtos.add(loanMapper.toDto(item));
            }

            if (!loanDtos.isEmpty()) {
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Shown successfully");
                responseMsg.setData(loanDtos);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
//            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("showLoanById")
    public ResponseEntity<?> showLoan(@RequestParam Long account_id) {
        StatusMessageDto<LoanDto> responseMsg = new StatusMessageDto<LoanDto>();
        try {
            Loan loan = loanService.showLoan(account_id);

            if (loan != null) {
                LoanDto loanDto = loanMapper.toDto(loan);
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Shown successfully");
                responseMsg.setData(loanDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }

    @PostMapping("closeLoan")
    public ResponseEntity<?> closeLoan(@RequestParam Long account_id) {
        StatusMessageDto<LoanDto> responseMsg = new StatusMessageDto<LoanDto>();
        try {
            Loan loan = loanService.closeLoan(account_id);
            LoanDto loanDto = loanMapper.toDto(loan);

            responseMsg.setStatus(HttpStatus.OK.value());
            responseMsg.setMessage("Deposit closed successfully");
            responseMsg.setData(loanDto);

            return ResponseEntity.ok().body(responseMsg);
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return  ResponseEntity.badRequest().body(responseMsg);
        }
    }
}
