package com.bgjshop.backend.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String city;
    private String address;
    private Integer roleType;
    private byte[] passwordSalt;
    private byte[] passwordHash;
}
