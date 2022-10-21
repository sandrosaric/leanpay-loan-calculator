package com.example.loancalculatorapi.helper;

import com.example.loancalculatorapi.model.AmortizationPayment;
import com.example.loancalculatorapi.model.LoanDetail;

//creating and calculating the whole amortization schedule for created loan
public class AmortizationPaymentSchedule {

    public static LoanDetail create(LoanDetail loan){
        double monthlyPaymentForAmortizationSchedule = CalculationsHelper.loanPaymentCalculationFormula(loan.getLoanAmount(),
                loan.getInterestRate(),
                loan.getPaymentFrequency(),
                loan.getNumberOfPayments());



        int numberOfPayments = loan.getNumberOfPayments();

        double balanceOwned = loan.getLoanAmount();
        double interestRate = loan.getInterestRate();
        int periodForInterest = CalculationsHelper.getPeriodBasedOnPaymentFrequency(loan.getPaymentFrequency());

        for(int i = 1;i <= numberOfPayments;i++){
            int period = i;
            double interestAmount = (balanceOwned * (interestRate / 100)) / periodForInterest;
            interestAmount = Math.round(interestAmount * 100.0) / 100.0;
            double principalAmount = monthlyPaymentForAmortizationSchedule - interestAmount;
            principalAmount = Math.round(principalAmount * 100.0) / 100.0;
            balanceOwned = balanceOwned - principalAmount;
            balanceOwned = Math.round(balanceOwned * 100.0) / 100.0;

            if((balanceOwned <= 0 || balanceOwned <=1) && period == numberOfPayments){
                balanceOwned = 0;
            }
            AmortizationPayment payment = new AmortizationPayment(period,monthlyPaymentForAmortizationSchedule,principalAmount,interestAmount,balanceOwned);
            loan.getPayments().add(payment);
        }

        return loan;
    }
    }

