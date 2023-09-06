package com.loansystem.loansystem.repository;

import com.loansystem.loansystem.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}
