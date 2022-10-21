package com.example.loancalculatorapi.dto;

import com.example.loancalculatorapi.model.PaymentFrequency;
import com.example.loancalculatorapi.validators.RequiredFieldValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

// this is a dto class for the data that is coming from the client
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetailDtoFromClient {

    @RequiredFieldValidator
    private Double loanAmount;
    @RequiredFieldValidator
    private Double interestRate;

    @Min(value=1,message = "The value must be positive")
    @NotNull(message = "The value must not be null")
    private Integer numberOfPayments;
    private PaymentFrequency paymentFrequency;
}
