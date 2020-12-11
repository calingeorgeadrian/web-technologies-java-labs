package com.gameshop.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Game {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Integer stock;
}
