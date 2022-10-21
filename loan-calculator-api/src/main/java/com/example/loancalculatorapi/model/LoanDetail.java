package com.example.loancalculatorapi.model;

import com.example.loancalculatorapi.validators.RequiredFieldValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//entity model for details of one loan
@Entity
@Table(name="LOAN_DETAILS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;


    @Column(name="LOAN_AMOUNT",nullable = false)
    private Double loanAmount;


    @Column(name="INTEREST_RATE",nullable = false)
    private Double interestRate;


    @Column(name="NUMBER_OF_PAYMENTS",nullable = false)
    private Integer numberOfPayments;



    @Enumerated(value = EnumType.STRING)
    @Column(name="PAYMENT_FREQUENCY",nullable = false)
    private PaymentFrequency paymentFrequency;

    @Column(name="REQUEST_DATE",nullable = false)
    private Date requestDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="LOAN_DETAIL_ID",referencedColumnName = "id",nullable = false)
    private List<AmortizationPayment> payments = new ArrayList<>();




    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PAYMENT_DETAIL_ID",nullable = false)
    private PaymentDetail paymentDetail;


}
