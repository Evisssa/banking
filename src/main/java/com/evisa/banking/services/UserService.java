package com.evisa.banking.services;

import com.evisa.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);

}
