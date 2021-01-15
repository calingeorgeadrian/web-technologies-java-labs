package com.bgjshop.backend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderItem {
    private Long orderId;
    private Long gameId;
    private Float price;
    private Integer quantity;
}
