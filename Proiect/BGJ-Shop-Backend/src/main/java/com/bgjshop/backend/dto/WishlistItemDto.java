package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class WishlistItemDto {
    @NotNull
    private String userId;
    @NotNull
    private Long gameId;
    private String title;
    private String imageUrl;
    private Float price;
    private Float newPrice;
}
