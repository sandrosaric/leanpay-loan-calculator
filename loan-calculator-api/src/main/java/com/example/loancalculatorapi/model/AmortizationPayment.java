package com.example.loancalculatorapi.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//entity model for every amortization payment item
@Entity
@Table(name="AMORTIZATION_SCHEDULE_PAYMENTS")
@Data
@NoArgsConstructor
public class AmortizationPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="PERIOD")
    private int period;

    @Column(name="PAYMENT_AMOUNT")
    private double paymentAmount;

    @Column(name="PRINCIPAL_AMOUNT")
    private double principalAmount;

    @Column(name="INTERESTS_AMOUNT")
    private double interestsAmount;

    @Column(name="BALANCE_OWNED")
    private double balanceOwned;



    public AmortizationPayment(int period, double paymentAmount, double principalAmount, double interestsAmount, double balanceOwned) {
        this.period = period;
        this.paymentAmount = paymentAmount;
        this.principalAmount = principalAmount;
        this.interestsAmount = interestsAmount;
        this.balanceOwned = balanceOwned;
    }
}
