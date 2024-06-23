package com.evisa.banking.dto;

import com.evisa.banking.models.Role;
import com.evisa.banking.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Integer id;
    private  String firstName;
    private  String lastName;
    private String email;
    private String password;

    private Role role;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }
}
