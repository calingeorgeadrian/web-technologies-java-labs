package com.gameshop.demo.validators;

import com.gameshop.demo.dto.OrderDto;
import com.gameshop.demo.dto.OrderGameDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;

public class MaxCopiesValidator implements ConstraintValidator<MaxCopies, OrderDto> {
    @Override
    public boolean isValid(OrderDto dto, ConstraintValidatorContext constraintValidatorContext) {

        List<Integer> quantities = dto.getOrderGameList().stream().map(OrderGameDto::getGameQuantity).collect(Collectors.toList());

        return !quantities.stream().anyMatch(i -> i > 2);
    }
}