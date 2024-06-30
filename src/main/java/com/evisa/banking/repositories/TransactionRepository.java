package com.evisa.banking.repositories;


import com.evisa.banking.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    //find all transactions by user id
    List<Transaction> findAllByUserId(Integer id);
}
