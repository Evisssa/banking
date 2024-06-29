package com.evisa.banking.services.impl;

import com.evisa.banking.dto.AccountDto;
import com.evisa.banking.models.Account;
import com.evisa.banking.models.Address;
import com.evisa.banking.repositories.AddressRepository;
import com.evisa.banking.services.AddressService;
import com.evisa.banking.dto.AddressDto;
import com.evisa.banking.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final ObjectsValidator<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {


        // first validation
        // then transform the user dto to the user entity
        // then save the user and return the id of the user
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return repository.save(address).getId();
    }

    @Override
    public List<AddressDto> findall() {
        return repository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return repository.findById(id)
                .stream()
                .map(AddressDto::fromEntity)
                .findAny().orElseThrow(() ->  new EntityNotFoundException("The address with id : "+id+" was not found!"));

    }

    @Override
    public void delete(Integer id) {

        //First lets find the address
        // then if it exist lets delete it

        repository.findById(id)
                .stream()
                .map(AddressDto::fromEntity)
                .findAny().orElseThrow(() ->  new EntityNotFoundException("The address with id : "+id+" was not found!"));



    }
}
