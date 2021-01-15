package com.bgjshop.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PopularGameDto {
    private Long id;
    private String title;
    private String imageUrl;
    private Integer count;
}
