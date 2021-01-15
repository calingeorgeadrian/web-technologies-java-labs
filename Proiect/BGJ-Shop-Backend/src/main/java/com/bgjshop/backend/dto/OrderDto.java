package com.bgjshop.backend.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class OrderDto {
    private Long id;
    @NotNull
    private String userId;
    @Size(min=1, max=128)
    private String firstName;
    @Size(min=1, max=128)
    private String lastName;
    @Size(min=1, max=12)
    private String phone;
    @Size(min=1, max=128)
    private String country;
    @Size(min=1, max=128)
    private String city;
    @Size(min=1, max=128)
    private String address;
    private String note;
    @Min(value = 0, message = "Total should not be a negative value")
    private Float total;
    private String code;
    @Min(value = 0, message = "Status should not be a negative value")
    @Max(value = 3, message = "Status can not be higher than 3")
    private Integer status;
    private LocalDateTime datePlaced;
    private LocalDateTime dateDelivered;
    private List<OrderItemDto> items;
}
