package com.evisa.banking.repositories;

import com.evisa.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    //Optional<User> findBy
}
