package com.example.loancalculatorapi.helper;

import com.example.loancalculatorapi.model.PaymentFrequency;

//some calculations that are needed for some classes and testing
public class CalculationsHelper {


    public static double loanPaymentCalculationFormula(double loanAmount, double interestRate, PaymentFrequency paymentFrequency, int numberOfPayments) {
        int n = numberOfPayments;
        double i = interestRate / 100 / getPeriodBasedOnPaymentFrequency(paymentFrequency);
        double payment = (loanAmount * (i * Math.pow(1 + i,n))) / (Math.pow(1 + i,n) - 1);
        return Math.round(payment * 100.0) / 100.0;
    }



    public static double totalAmountPaidWithInterestCalculate(double payment, int numberOfMonths){
        double result = payment * numberOfMonths;
        return Math.round(result * 100.0) / 100.0;
    }


   public static double totalInterestPaidCalculate(double totalAmount, double loanAmount){
        double result = totalAmount - loanAmount;
        return Math.round(result * 100.0) / 100.0;
    }



    public static int getPeriodBasedOnPaymentFrequency(PaymentFrequency paymentFrequency){

        int n = 0;

         switch (paymentFrequency){
             case DAILY:
                 n = 365;
                 break;
             case WEEKLY:
                 n = 52;
                 break;
             case BIWEEKLY:
                 n = 26;
                 break;
             case MONTHLY:
                 n = 12;
                 break;
             case SEMI_MONTH:
                 n = 12;
                 break;
             case BIMONTHLY:
                 n = 6;
                 break;
             case QUARTERLY:
                 n = 4;
                 break;
             case SEMI_ANNUAL:
                 n = 2;
                 break;
             case ANNUAL:
                 n = 1;
                 break;
             default:
                 n = 0;
                 break;


        }
        return n;
    }



}
