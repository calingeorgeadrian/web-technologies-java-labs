package com.gameshop.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class OrderDto {
    @NotNull
    @Pattern(regexp = "^(([a-z]+[0-9]*) ?)*$")
    @Size(min=4, max = 5)
    private String orderId;
    @Pattern(regexp = "^([0]?[1-9]|[1|2][0-9]|[3][0|1])[-]([0]?[1-9]|[1][0-2])[-]([0-9]{4}|[0-9]{2})$", message = "Date should match format DD-MM-YYYY")
    private String orderDate;
    @Size(min=3, max=32)
    private String orderCustomerName;
    @Size(min=3, max=32)
    private String orderAddress;
    private List<GameDto> orderGameList;
}
