package com.evisa.banking.dto;

import com.evisa.banking.models.Address;
import com.evisa.banking.models.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressDto {
    private Integer id;
    private String street;
    private Integer zipCode;
    private Integer houseNumber;
    private String city;
    private String country;
    private Integer userId;

    public static AddressDto fromEntity(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .houseNumber(address.getHouseNumber())
                .city(address.getCity())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();
    }

    public static Address toEntity(AddressDto addressDto){
        return Address.builder()
                .id(addressDto.getId())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode())
                .houseNumber(addressDto.getHouseNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .user(
                        User.builder()
                                .id(addressDto.getUserId())
                                .build()
                )
                .build();
    }

}
