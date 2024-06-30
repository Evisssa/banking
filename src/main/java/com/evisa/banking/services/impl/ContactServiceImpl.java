package com.evisa.banking.services.impl;

import com.evisa.banking.dto.ContactDto;
import com.evisa.banking.dto.TransactionDto;
import com.evisa.banking.models.Contact;
import com.evisa.banking.models.User;
import com.evisa.banking.repositories.ContactRepository;
import com.evisa.banking.services.ContactService;
import com.evisa.banking.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ObjectsValidator<ContactDto> validator;

    @Override
    public Integer save(ContactDto dto) {
        //return null;

        // first validation
        // then transform the dto to the entity
        // then save the user and return the id of the user

        validator.validate(dto);
        Contact contact = ContactDto.toEntity(dto);
        return repository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findall() {
        return repository.findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ContactDto findById(Integer id) {
        return repository.findById(id)
                .stream()
                .map(ContactDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("No Contact entity found with the provided ID : "+id));
    }

    @Override
    public void delete(Integer id) {
        ContactDto contactDto =  repository.findById(id)
                .stream()
                .map(ContactDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("No Contact entity found with the provided ID : "+id));
        validator.validate(contactDto);
        repository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());
    }
}
