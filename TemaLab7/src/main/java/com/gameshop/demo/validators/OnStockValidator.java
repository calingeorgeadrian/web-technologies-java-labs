package com.gameshop.demo.validators;

import com.gameshop.demo.dto.OrderDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OnStockValidator implements ConstraintValidator<OnStock, OrderDto> {
    @Override
    public boolean isValid(OrderDto dto, ConstraintValidatorContext constraintValidatorContext) {

        List<Integer> stocks = dto.getOrderGameList().stream().map((gameDto -> gameDto.getGameStock())).collect(Collectors.toList());

        return !stocks.contains(0);
    }
}
