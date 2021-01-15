package com.bgjshop.backend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CartItem {
    private String userId;
    private Long gameId;
    private Integer quantity;
}
