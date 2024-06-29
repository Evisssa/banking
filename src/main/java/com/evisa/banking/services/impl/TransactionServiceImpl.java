package com.evisa.banking.services.impl;

import com.evisa.banking.dto.TransactionDto;
import com.evisa.banking.models.Transaction;
import com.evisa.banking.models.TransactionType;
import com.evisa.banking.repositories.TransactionRepository;
import com.evisa.banking.services.TransactionService;
import com.evisa.banking.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final ObjectsValidator<TransactionDto> validator;

    @Override
    public Integer save(TransactionDto dto) {

        // first validation
        // then transform the dto to the entity
        // then save the user and return the id of the user

        validator.validate(dto);
        Transaction transaction = TransactionDto.toEntity(dto);
        BigDecimal transactionMultiplier = BigDecimal.valueOf(transactionType(transaction.getType()));
        BigDecimal ammount = transaction.getAmount().multiply(transactionMultiplier);
        transaction.setAmount(ammount);
        return repository.save(transaction).getId();

    }

    @Override
    public List<TransactionDto> findall() {
        return repository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return repository.findById(id)
                .map(TransactionDto::fromEntity)
                . orElseThrow(() -> new EntityNotFoundException("No Transaction entity found with the provided ID : "+id));
    }

    @Override
    public void delete(Integer id) {
        TransactionDto transactionDto = repository.findById(id)
                .map(TransactionDto::fromEntity)
                . orElseThrow(() -> new EntityNotFoundException("No transaction entity found with the provided ID : "+id));
        validator.validate(transactionDto);
        repository.deleteById(id);

    }


    private int transactionType(TransactionType type){
        return (TransactionType.TRANSFERT == type )? 1: -1;

    }
}
