package com.evisa.banking.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntity{


    private  String firstName;
    private  String lastName;
    private LocalDate birthDate;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean isActive;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts;
    //address of type Address
    @OneToOne
    private Address address;
    @OneToOne
    private Account account;
    @OneToOne
    private Role role;


/*
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastUpdatedDate;

*/






}
