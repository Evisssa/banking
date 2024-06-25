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
    public UserDto findById(Integer Id) {
       return repository.findById(Id)
               .stream()
               .map(UserDto::fromEntity)
               .findAny().orElseThrow(() -> new EntityNotFoundException("No user entity found with the provided ID "+Id));
              // .collect(Collectors.);
    }

    @Override
    public void delete(Integer Id) {
        //validator.validate(dto);
        UserDto userDto = repository.findById(Id)
                .stream()
                .map(UserDto::fromEntity)
                .findAny().orElseThrow(() -> new EntityNotFoundException("No user entity found with the provided ID "+Id));
        validator.validate(userDto);
        repository.deleteById(Id);

    }
}
