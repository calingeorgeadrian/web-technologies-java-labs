package com.gameshop.demo.dto;

import com.gameshop.demo.validators.MaxCopies;
import com.gameshop.demo.validators.OnStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@MaxCopies(message="You can not order more than 2 copies of the same game.")
@OnStock(message="All games must be in stock.")
public class OrderDto {
    @NotNull
    private Long orderId;
    @Pattern(regexp = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]|[1][0-2])[-]([0-9]{4}|[0-9]{2})$", message = "Date should match format DD-MM-YYYY")
    private String orderDate;
    @Size(min=3, max=32)
    private String orderCustomerName;
    @Size(min=3, max=32)
    private String orderAddress;
    private List<OrderGameDto> orderGameList;
}
