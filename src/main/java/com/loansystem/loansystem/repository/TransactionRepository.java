package com.loansystem.loansystem.repository;

import com.loansystem.loansystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(value = "SELECT * FROM Transaction t WHERE t.loan_id = :loan_id", nativeQuery = true)
    List<Transaction> findAllByLoan(@Param("loan_id") Long loan_id);

    @Query(value = "SELECT * FROM Transaction t WHERE t.loan_id = :loan_id AND DATEDIFF(date(t.created), :start ) >= 0 AND DATEDIFF(date(t.created), :finish ) <= 0", nativeQuery = true)
    List<Transaction> findAllByDate(@Param("loan_id") Long loan_id, @Param("start") Date start, @Param("finish") Date finish);

}
