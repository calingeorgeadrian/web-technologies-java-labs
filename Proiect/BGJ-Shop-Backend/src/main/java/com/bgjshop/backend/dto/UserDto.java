package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDto {
    private UUID id;
    @Size(min=1, max=256)
    private String email;
    @Size(min=1, max=128)
    private String firstName;
    @Size(min=1, max=128)
    private String lastName;
    private String phone;
    private String country;
    private String city;
    private String address;
    @Min(value = 0, message = "Stock should not be a negative value")
    @Max(value = 1, message = "Role can not be higher than 1")
    private Integer roleType;
    private byte[] passwordSalt;
    private byte[] passwordHash;
}
