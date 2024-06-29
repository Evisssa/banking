package com.evisa.banking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Transaction extends AbstractEntity{


    private BigDecimal amount;



    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String destinationIban;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
