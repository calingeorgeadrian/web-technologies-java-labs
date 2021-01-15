package com.bgjshop.backend.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Game {
    private Long id;
    private Long bggId;
    private String title;
    private String type;
    private String imageUrl;
    private String description;
    private Integer minPlayers;
    private Integer maxPlayers;
    private Integer playingTime;
    private Integer year;
    private Float price;
    private Integer stock;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
}
