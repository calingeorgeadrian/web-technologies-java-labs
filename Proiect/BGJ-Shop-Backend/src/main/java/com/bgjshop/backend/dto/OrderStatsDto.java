package com.bgjshop.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderStatsDto {
    private Integer total;
    private Integer pending;
    private Integer canceled;
    private Integer finished;
}
