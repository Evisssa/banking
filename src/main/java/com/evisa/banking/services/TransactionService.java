package com.evisa.banking.services;

import com.evisa.banking.dto.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{

    List<TransactionDto> findAllByUserID(Integer id);
}
