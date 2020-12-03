package com.gameshop.demo.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {OnStockValidator.class})
public @interface OnStock {
    String message() default "Invalid request";

    Class<?> [] groups() default {};

    Class<? extends Payload> [] payload() default {};
}