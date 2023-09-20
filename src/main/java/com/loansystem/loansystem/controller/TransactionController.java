package com.loansystem.loansystem.controller;

import com.loansystem.loansystem.mapper.TransactionMapper;
import com.loansystem.loansystem.model.Transaction;
import com.loansystem.loansystem.modeldto.StatusMessageDto;
import com.loansystem.loansystem.modeldto.TransactionDto;
import com.loansystem.loansystem.service.TransactionService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private TransactionService transactionService;
    private TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @PostMapping("showTransaction")
    public ResponseEntity<?> showTransaction(@RequestParam Long loan_id) {
        StatusMessageDto<List<TransactionDto>> responseMsg = new StatusMessageDto<List<TransactionDto>>();

        try{
            List<Transaction> transactions = transactionService.showTransaction(loan_id);
            List<TransactionDto> transactionsDto = new ArrayList<TransactionDto>();
            for (Transaction item:transactions) {
                transactionsDto.add(transactionMapper.toDto(item));
            }

            if(!transactionsDto.isEmpty()) {
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Transaction shown successfully");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseMsg);
        }

    }

    @PostMapping("showTransaction/filterByDate")
    public ResponseEntity<?> showTransaction(@RequestParam Long loan_id, Date startDate, Date finishDate) {
        StatusMessageDto<List<TransactionDto>> responseMsg = new StatusMessageDto<List<TransactionDto>>();

        try{
            List<Transaction> transactions = transactionService.showTransactionByDate(loan_id, startDate, finishDate);
            List<TransactionDto> transactionsDto = new ArrayList<TransactionDto>();
            for (Transaction item:transactions) {
                transactionsDto.add(transactionMapper.toDto(item));
            }

            if(!transactionsDto.isEmpty()) {
                responseMsg.setStatus(HttpStatus.OK.value());
                responseMsg.setMessage("Transaction shown successfully");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.ok().body(responseMsg);
            } else {
                responseMsg.setStatus(HttpStatus.NOT_FOUND.value());
                responseMsg.setMessage("Not found");
                responseMsg.setData(transactionsDto);

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMsg);
            }
        } catch(Exception e) {
            responseMsg.setStatus(HttpStatus.BAD_REQUEST.value());
            responseMsg.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseMsg);
        }
    }
    // date = "mm/dd/yyyy"

    @PostMapping("repayment")
    public ResponseEntity<?> repayment(@RequestParam double nominal, @RequestParam long account_id) {
        StatusMessageDto<TransactionDto> responseMsg = new StatusMessageDto<TransactionDto>();
        try {
            TransactionDto transactionDto = transactionMapper.toDto(transactionService.repayment(nominal, account_id));

            responseMsg.setStatus(HttpStatus.OK.value());
            responseMsg.setData(transactionDto);
            responseMsg.setMessage("Repayment successful");

            return ResponseEntity.ok().body(responseMsg);
        } catch (Exception e) {
            responseMsg.setMessage(e.getMessage());

            return ResponseEntity.badRequest().body(responseMsg);
        }
    }

//    @PostMapping("interestAccrual")
//    public Transaction interestAccrual(@RequestParam double nominal, @RequestParam long loan_id) {
//        return transactionService.interestAccrual(loan_id, nominal);
//    }

}
