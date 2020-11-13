package com.homework.gameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class GameDto {

    @NotNull
    @Pattern(regexp = "^(([a-z]+[0-9]*) ?)*$")
    @Size(min=3, max = 4)
    private String gameId;

    @Size(min=3, max=16)
    private String gameTitle;

    @Size(min=3, max=32)
    private String gameDescription;

    @Min(value = 1, message = "Price should not be less than 1")
    private int gamePrice;

    @Min(value = 1, message = "Min player number should not be less than 1")
    private int gameMinPlayers;

    @Min(value = 1, message = "Max player number should not be less than 1")
    private int gameMaxPlayers;
}
