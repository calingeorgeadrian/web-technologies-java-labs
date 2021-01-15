package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CodeDto {
    @NotNull
    @Size(min=1, max=32)
    private String code;
    @NotNull
    private Integer type;
    @Min(value = 1, message = "Discount should not be less than 1")
    private Integer discount;
}
