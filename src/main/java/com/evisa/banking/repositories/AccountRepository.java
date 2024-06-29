package com.evisa.banking.repositories;

import com.evisa.banking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer>{

    // find by iban
   Optional<Account> findByIban(String iban);
   Optional<Account>  findByUserId(Integer id);
}
