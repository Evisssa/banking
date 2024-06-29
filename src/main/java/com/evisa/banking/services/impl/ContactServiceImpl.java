package com.evisa.banking.services.impl;

import com.evisa.banking.dto.ContactDto;
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

    public final ContactRepository repository;
    public final ObjectsValidator<ContactDto> validator;

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
                .findAny().orElseThrow(() -> new EntityNotFoundException("The Contact with id : "+id+" was not found!"));
    }

    @Override
    public void delete(Integer id) {
        ContactDto contactDto =  repository.findById(id)
                .stream()
                .map(ContactDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("The Contact with id : "+id+" was not found!"));
        validator.validate(contactDto);
        repository.deleteById(id);
    }
}
