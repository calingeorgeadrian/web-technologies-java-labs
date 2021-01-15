package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
@Builder
public class GameDto {
    private Long id;
    private Long bggId;
    @Size(min=1, max=128)
    private String title;
    @Size(min=1, max=128)
    private String type;
    private String imageUrl;
    private String description;
    @Min(value = 1, message = "Minimum number of players should not be less than 1")
    private Integer minPlayers;
    @Min(value = 1, message = "Maximum number of players should not be less than 1")
    private Integer maxPlayers;
    private Integer playingTime;
    @Min(value = 0, message = "Age should not be a negative value")
    private Integer year;
    @Min(value = 1, message = "Price should not be less than 1")
    private Float price;
    private Float newPrice;
    @Min(value = 0, message = "Stock should not be a negative value")
    private Integer stock;
    private LocalDateTime dateAdded;
    private LocalDateTime dateModified;
}