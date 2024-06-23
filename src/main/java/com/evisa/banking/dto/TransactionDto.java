package com.evisa.banking.dto;

import com.evisa.banking.models.Transaction;
import com.evisa.banking.models.TransactionType;
import com.evisa.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDto {
    private Integer id;
    private BigDecimal amount;
    private TransactionType type;
    private String destinationIban;
    private Integer userId;
    public static TransactionDto fromEntity(Transaction transaction){

        return TransactionDto.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }

    public static Transaction toEntity(TransactionDto transaction){

        return Transaction.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .amount(transaction.getAmount())
                .destinationIban(transaction.getDestinationIban())
                .user(
                        User.builder()
                                .id(transaction.getUserId())
                                .build()
                )
                .build();
    }




}
