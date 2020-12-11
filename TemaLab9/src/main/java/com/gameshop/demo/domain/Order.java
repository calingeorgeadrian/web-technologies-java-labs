package com.gameshop.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Order {
    private Long id;
    private String customerName;
    private String address;
    private String date;
}
