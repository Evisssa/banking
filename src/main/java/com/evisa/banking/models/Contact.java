package com.evisa.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Contact extends AbstractEntity{


    private  String firstName;
    private  String lastName;
    private String email;
    private String password;
    private String iban;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
