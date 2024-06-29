package com.evisa.banking.services.impl;

import com.evisa.banking.dto.AccountDto;
import com.evisa.banking.dto.UserDto;
import com.evisa.banking.models.User;
import com.evisa.banking.repositories.AccountRepository;
import com.evisa.banking.repositories.UserRepository;
import com.evisa.banking.services.AccountService;
import com.evisa.banking.services.UserService;
import com.evisa.banking.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final AccountService accountService;
    private final ObjectsValidator<UserDto> validator;
    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto); // first validation
        User user = UserDto.toEntity(dto); // then transform the user dto to the user entity
        return repository.save(user).getId(); // then save the user and return the id of the user
    }

    @Override
    public List<UserDto> findall() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
       return repository.findById(id)
               .stream()
               .map(UserDto::fromEntity)
               .findAny().orElseThrow(() -> new EntityNotFoundException("No user entity found with the provided ID : "+id));
              // .collect(Collectors.);
    }

    @Override
    public void delete(Integer id) {
        //validator.validate(dto);
        UserDto userDto = repository.findById(id)
                .stream()
                .map(UserDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("No user entity found with the provided ID : "+id));
        validator.validate(userDto);
        repository.deleteById(id);

    }

    @Override
    public Integer validateAccount(Integer id) {
        //First find the user entity
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found for user account validation : "+id));
        //

        //Lets make the user active
        user.setIsActive(true);
        // Then create a bank account for the user
        AccountDto accountDto = AccountDto.builder()
                .user( UserDto.fromEntity(user))
                .build();

        // then save via the account repository
        accountService.save(accountDto);
        repository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user found for user account validation : "+id));
        //

        //Lets make the user active
        user.setIsActive(false);
    }
}
