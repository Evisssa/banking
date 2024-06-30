package com.evisa.banking.repositories;

import com.evisa.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    Optional<Contact> findAllByUserId(Integer userId);
}
