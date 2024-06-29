package com.evisa.banking.services.impl;

import com.evisa.banking.dto.AccountDto;
import com.evisa.banking.dto.UserDto;
import com.evisa.banking.exceptions.OperationNonPermitedExample;
import com.evisa.banking.models.Account;
import com.evisa.banking.repositories.AccountRepository;
import com.evisa.banking.services.AccountService;
import com.evisa.banking.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final ObjectsValidator<AccountDto> validator;
    @Override
    public Integer save(AccountDto dto) {
       /*
        if(dto.getId() != null)
        {
            throw new OperationNonPermitedExample(
                    "Account can not be updated!",
                    "Save account",
                    "Account",
                    "Update not permited!"
            );
        } */

        validator.validate(dto); // first validation
        Account account = AccountDto.toEntity(dto); // then transform the user dto to the user entity
        boolean userHasAlreadyAnAccount = repository.findByUserId(account.getUser().getId()).isPresent();
        if(userHasAlreadyAnAccount)
        {
            throw new OperationNonPermitedExample(
                    "User can not have more than one account!",
                    "Creae account",
                    "Account Service",
                    "Account creation"
            );
        }
        account.setIban(generateRandomIban());
        return repository.save(account).getId(); // then save the user and return the id of the user
    }

    @Override
    public List<AccountDto> findall() {
        return repository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return repository.findById(id)
                .stream()
                .map(AccountDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("No Account entity found with the provided ID : "+id));
        // .collect(Collectors.);
    }

    @Override
    public void delete(Integer id) {

        AccountDto accountDto = repository.findById(id)
                .stream()
                .map(AccountDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("No Account entity found with the provided ID "+id));
        validator.validate(accountDto);
        repository.deleteById(id);

    }

    private String generateRandomIban(){
        String iban = Iban.random(CountryCode.AL).toFormattedString();
        Boolean ibanExists =  repository.findByIban(iban).isPresent();

        if(ibanExists){
            return generateRandomIban();
        }

        return iban;






    }
}
