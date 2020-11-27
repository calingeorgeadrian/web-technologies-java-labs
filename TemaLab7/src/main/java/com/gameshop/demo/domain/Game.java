package com.gameshop.demo.domain;

import lombok.*;

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
}
