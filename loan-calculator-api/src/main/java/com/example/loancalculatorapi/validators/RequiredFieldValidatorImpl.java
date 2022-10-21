package com.example.loancalculatorapi.validators;

import com.example.loancalculatorapi.dto.LoanDetailDtoFromClient;
import com.example.loancalculatorapi.model.LoanDetail;

import javax.swing.text.html.parser.Parser;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//implementation of the custom validation class
public class RequiredFieldValidatorImpl implements ConstraintValidator<RequiredFieldValidator, Double> {
    @Override
    public void initialize(RequiredFieldValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Double contactField, ConstraintValidatorContext constraintValidatorContext) {
        if(contactField == null){
            return false;
        }

        if(contactField == 0d){
            return false;
        }


        return true;
    }
}
