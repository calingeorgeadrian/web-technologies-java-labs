package com.homework.gameshop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Game {
    private String id;
    private String title;
    private String description;
    private int price;
    private int minPlayers;
    private int maxPlayers;
}
