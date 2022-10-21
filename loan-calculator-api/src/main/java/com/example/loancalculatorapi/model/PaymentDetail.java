package com.example.loancalculatorapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//entity model for simple payment details for one loan
@Entity
@Data
@Table(name="PAYMENT_DETAILS")
@NoArgsConstructor
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="MONTHLY_PAYMENT")
    private double monthlyPayment;

    @Column(name="TOTAL_PAYMENT")
    private double totalPayment;

    @Column(name="TOTAL_INTEREST_PAID")
    private double totalInterestPaid;

    

    public PaymentDetail(double monthlyPayment, double totalPayment, double totalInterestPaid) {
        this.monthlyPayment = monthlyPayment;
        this.totalPayment = totalPayment;
        this.totalInterestPaid = totalInterestPaid;
    }
}
