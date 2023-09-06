package com.loansystem.loansystem.repository;

import com.loansystem.loansystem.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
