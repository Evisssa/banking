package com.evisa.banking.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Address extends AbstractEntity{

    @Id
    @GeneratedValue
    private Integer id;
    private String street;
    private Integer zipCode;
    private Integer houseNumber;
    private String city;
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
