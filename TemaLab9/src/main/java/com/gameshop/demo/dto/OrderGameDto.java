package com.gameshop.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderGameDto {

    private Long orderId;

    @NotNull
    private Long gameId;

    @Size(min=3, max=128)
    private String gameTitle;

    private Integer gameStock;

    @Min(value = 1, message = "Price should not be less than 1")
    private Integer gamePrice;

    @Min(value = 1, message = "Quantity should not be less than 1")
    @Max(value = 2, message = "You can not order more than 2 copies of the same game.")
    private Integer gameQuantity;

}
