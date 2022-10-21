package com.example.loancalculatorapi;

import com.example.loancalculatorapi.helper.AmortizationPaymentSchedule;
import com.example.loancalculatorapi.helper.CalculationsHelper;
import com.example.loancalculatorapi.helper.PaymentDetails;
import com.example.loancalculatorapi.model.LoanDetail;
import com.example.loancalculatorapi.model.PaymentFrequency;
import com.example.loancalculatorapi.repository.LoanDetailRepository;
import lombok.Data;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.ParameterMetaData;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//integration test for the data layer
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoanDetailRepositoryTests {

    @Autowired
    private LoanDetailRepository loanDetailRepository;


    // JUnit test for saveLoanDetail
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveLoanDetailTest(){

        LoanDetail loanDetailForCreation = new LoanDetail();
        loanDetailForCreation.setLoanAmount(240000d);
        loanDetailForCreation.setInterestRate(4.875);
        loanDetailForCreation.setNumberOfPayments(12);
        loanDetailForCreation.setPaymentFrequency(PaymentFrequency.MONTHLY);



         loanDetailForCreation = PaymentDetails.create(loanDetailForCreation);
         loanDetailForCreation = AmortizationPaymentSchedule.create(loanDetailForCreation);
         LoanDetail loanDetailSaved = this.loanDetailRepository.save(loanDetailForCreation);

         assertThat(loanDetailSaved.getId()).isGreaterThan(0);

    }


    //JUnit test for getLoanDetailById
    @Test
    @Order(3)
    public void getLoanDetailByIdTest(){
        LoanDetail loanDetail = this.loanDetailRepository.findById(1L).get();

        assertThat(loanDetail.getId()).isEqualTo(1L);
    }


    //JUnit test for getAllLoanDetails
    @Test
    @Order(2)
    public void getAllLoanDetailsTest(){
        List<LoanDetail> loanDetailList = this.loanDetailRepository.findAll();
        assertThat(loanDetailList.size()).isGreaterThan(0);
    }

    //JUnit updateLoanDetail test

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateLoanDetailTest(){
        LoanDetail loanDetailForUpdate = this.loanDetailRepository.findById(1L).get();
        loanDetailForUpdate.setLoanAmount(4000d);
        this.loanDetailRepository.save(loanDetailForUpdate);
        assertThat(loanDetailForUpdate.getLoanAmount()).isEqualTo(4000d);
        
    }

    //JUnit deleteLoanDetail Test
    @Test
    @Order(5)
    public void deleteLoanDetailByIdTest(){
        this.loanDetailRepository.deleteById(1l);
        Optional<LoanDetail> findLoanDetailById = this.loanDetailRepository.findById(1l);
        LoanDetail existingLoanDetail = null;
        if(findLoanDetailById.isPresent()){
            existingLoanDetail = findLoanDetailById.get();
        }
        assertThat(existingLoanDetail).isNull();


    }


}
