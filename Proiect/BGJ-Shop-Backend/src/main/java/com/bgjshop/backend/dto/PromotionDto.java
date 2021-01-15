package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PromotionDto {
    private Long id;
    @Size(min=1, max=128)
    private String title;
    @Min(value = 1, message = "Discount should not be less than 1")
    @Max(value = 100, message = "Discount should not be higher than 100")
    private Integer discount;
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
}
