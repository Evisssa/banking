package com.evisa.banking.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity  {
    @Id
    @GeneratedValue
    private Integer id;
    @CreatedDate
    @Column(name="createdDate", nullable = false,updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(name="lastModifiedDate",insertable = false)
    private LocalDateTime lastModifiedDate;
}
