package com.example.loancalculatorapi.dto;

import com.example.loancalculatorapi.model.AmortizationPayment;
import com.example.loancalculatorapi.model.PaymentDetail;
import com.example.loancalculatorapi.model.PaymentFrequency;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//this is a dto class for the data that is going to be my server response data
@Data
public class LoanDetailDto implements Serializable {

    private Long id;
    private Double loanAmount;
    private Double interestRate;
    private Integer numberOfPayments;
    private PaymentFrequency paymentFrequency;
    private Date requestDate;
    private List<AmortizationPayment> payments;
    private PaymentDetail paymentDetail;
}
