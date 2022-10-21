package com.example.loancalculatorapi.helper;

import com.example.loancalculatorapi.model.LoanDetail;
import com.example.loancalculatorapi.model.PaymentDetail;

//create and calculate all simple payment details for created loan
public class PaymentDetails {
    public static LoanDetail create(LoanDetail loan){
        double monthlyPayment = CalculationsHelper.loanPaymentCalculationFormula(loan.getLoanAmount(),
                loan.getInterestRate(),
                loan.getPaymentFrequency(),
                loan.getNumberOfPayments());

        double totalPayment = CalculationsHelper.totalAmountPaidWithInterestCalculate(monthlyPayment,loan.getNumberOfPayments());


        double totalInterestPaid = CalculationsHelper.totalInterestPaidCalculate(totalPayment,loan.getLoanAmount());


        PaymentDetail spd = new PaymentDetail(monthlyPayment,totalPayment,totalInterestPaid);

        loan.setPaymentDetail(spd);

        return loan;
    }
}
