package com.gameshop.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderGame {
    private Long orderId;
    private Long gameId;
    private Integer quantity;
}
