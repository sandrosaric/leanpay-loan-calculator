package com.example.loancalculatorapi.helper;

import com.example.loancalculatorapi.model.LoanDetail;
import com.example.loancalculatorapi.model.PaymentFrequency;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

//simple unit tests for some important methods
class CalculationsHelperTest {


    @Test
    void loanPaymentCalculationFormula() {
        LoanDetail loanDetail = LoanDetail.builder().loanAmount(6000d)
                .interestRate(5d)
                .numberOfPayments(4)
                .paymentFrequency(PaymentFrequency.MONTHLY)
                .requestDate(new Date())
                .build();

        assertThat(CalculationsHelper
                .loanPaymentCalculationFormula(loanDetail.getLoanAmount(),loanDetail.getInterestRate(),
                loanDetail.getPaymentFrequency(),loanDetail.getNumberOfPayments())).isEqualTo(1515.66);
    }

    @Test
    void totalAmountPaidWithInterestCalculate() {
        double monthlyPayment = 1515.66;
        int numberOfMonths = 4;
        assertThat(CalculationsHelper
                .totalAmountPaidWithInterestCalculate(monthlyPayment,numberOfMonths)).isEqualTo(6062.64);
    }

    @Test
    void totalInterestPaidCalculate() {
        double totalAmount = 6062.64;
        double loanAmount = 6000d;
        assertThat(CalculationsHelper
                .totalInterestPaidCalculate(totalAmount,loanAmount)).isEqualTo(62.64);
    }

    @Test
    void getPeriodBasedOnPaymentFrequency() {
        PaymentFrequency paymentFrequency = PaymentFrequency.MONTHLY;
        assertThat(CalculationsHelper.getPeriodBasedOnPaymentFrequency(PaymentFrequency.MONTHLY)).isEqualTo(12);
    }


}