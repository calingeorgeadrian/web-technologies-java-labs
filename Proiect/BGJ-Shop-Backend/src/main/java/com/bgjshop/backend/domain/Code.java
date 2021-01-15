package com.bgjshop.backend.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Code {
    private String code;
    private Integer type;
    private Integer discount;
}
