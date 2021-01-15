package com.bgjshop.backend.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ReportDto {
    private Float revenue;
    private Integer canceled;
    private Integer orders;
}
