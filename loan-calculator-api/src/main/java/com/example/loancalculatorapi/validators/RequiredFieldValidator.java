package com.example.loancalculatorapi.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//interface for the custom validation class
@Constraint(validatedBy = RequiredFieldValidatorImpl.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredFieldValidator {
    String message() default "The field is required and must not be null or blank or 0!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}