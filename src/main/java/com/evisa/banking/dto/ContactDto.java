package com.evisa.banking.dto;

import com.evisa.banking.models.Contact;
import com.evisa.banking.models.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDto {

    private Integer id;
    private  String firstName;
    private  String lastName;
    private String email;
    private String password;
    private String iban;
    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .password(contact.getPassword())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact toEntity(ContactDto contactDto){
        return Contact.builder()
                .id(contactDto.getId())
                .firstName(contactDto.getFirstName())
                .lastName(contactDto.getLastName())
                .email(contactDto.getEmail())
                .password(contactDto.getPassword())
                .iban(contactDto.getIban())
                .user(
                        User.builder()
                                .id(contactDto.getUserId())
                                .build()
                )
                .build();
    }
}
