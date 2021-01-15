package com.bgjshop.backend.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Promotion {
    private Long id;
    private String title;
    private Integer discount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
