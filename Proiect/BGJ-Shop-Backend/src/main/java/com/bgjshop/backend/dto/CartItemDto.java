package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CartItemDto {
    @NotNull
    private String userId;
    @NotNull
    private Long gameId;
    private String title;
    private String imageUrl;
    @Min(value = 0, message = "Price should not be a negative value")
    private Float price;
    private Float newPrice;
    @Min(value = 0, message = "Quantity should not be a negative value")
    private Integer quantity;
}
