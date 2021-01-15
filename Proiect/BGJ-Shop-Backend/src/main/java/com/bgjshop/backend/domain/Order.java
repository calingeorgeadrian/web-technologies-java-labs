package com.bgjshop.backend.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String country;
    private String city;
    private String address;
    private String note;
    private Float total;
    private String code;
    private Integer status;
    private LocalDateTime datePlaced;
    private LocalDateTime dateDelivered;
}
