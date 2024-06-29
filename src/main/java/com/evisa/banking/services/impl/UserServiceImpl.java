package com.evisa.banking.services.impl;

import com.evisa.banking.dto.UserDto;
import com.evisa.banking.models.User;
import com.evisa.banking.repositories.UserRepository;
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
        return null;
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        return null;
    }
}
