package com.gameshop.demo.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class GameDto {

    @NotNull
    private Long gameId;

    @Size(min=3, max=128)
    private String gameTitle;

    @Size(min=3, max=256)
    private String gameDescription;

    @Min(value = 1, message = "Price should not be less than 1")
    private int gamePrice;
    @Min(value = 0, message = "Stock should not be a negative value")
    private int gameStock;
}