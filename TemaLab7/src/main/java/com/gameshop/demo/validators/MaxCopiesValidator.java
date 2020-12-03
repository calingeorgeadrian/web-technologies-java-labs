package com.gameshop.demo.validators;

import com.gameshop.demo.dto.GameDto;
import com.gameshop.demo.dto.OrderDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MaxCopiesValidator implements ConstraintValidator<MaxCopies, OrderDto> {
    @Override
    public boolean isValid(OrderDto dto, ConstraintValidatorContext constraintValidatorContext) {

        List<String> gameIds = dto.getOrderGameList().stream().map(GameDto::getGameId).collect(Collectors.toList());

        List<Boolean> checks = new ArrayList<>();

        for(int i = 0; i < gameIds.size(); i++) {
            checks.add(Collections.frequency(gameIds, (gameIds.toArray())[i]) > 2);
        }

        return !checks.contains(true);
    }
}