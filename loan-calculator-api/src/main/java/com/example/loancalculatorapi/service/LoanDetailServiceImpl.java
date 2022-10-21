package com.example.loancalculatorapi.service;

import com.example.loancalculatorapi.dto.LoanDetailDto;
import com.example.loancalculatorapi.dto.LoanDetailDtoFromClient;
import com.example.loancalculatorapi.exception.ResourceNotFoundException;
import com.example.loancalculatorapi.helper.AmortizationPaymentSchedule;
import com.example.loancalculatorapi.helper.CalculationsHelper;
import com.example.loancalculatorapi.helper.PaymentDetails;
import com.example.loancalculatorapi.model.LoanDetail;
import com.example.loancalculatorapi.model.AmortizationPayment;
import com.example.loancalculatorapi.model.PaymentDetail;
import com.example.loancalculatorapi.repository.LoanDetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//implementation of the main bussines logic
@Service
public class LoanDetailServiceImpl implements LoanDetailService {
    private final LoanDetailRepository loanDetailRepository;

    private final ModelMapper modelMapper;



    public LoanDetailServiceImpl(LoanDetailRepository loanCalculationRepository, ModelMapper modelMapper) {
        this.loanDetailRepository = loanCalculationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LoanDetailDto saveLoanDetails(LoanDetailDtoFromClient loanDetailDtoFromClient) {

         LoanDetail loan = mapToEntity(loanDetailDtoFromClient);

         loan.setRequestDate(new Date());

         loan = setPaymentDetailAndAmortizationPaymentSchedule(loan);

        return mapToDTO(this.loanDetailRepository.save(loan));

    }



    @Override
    public boolean deleteLoanDetailsById(Long id) {
        boolean success = false;
        Optional<LoanDetail> existingLoanDetail = this.loanDetailRepository.findById(id);
        if(existingLoanDetail.isPresent()){
            this.loanDetailRepository.deleteById(id);
            success = true;
        }
        else{
            throw new ResourceNotFoundException("Loan Details","ID",id);
        }

        return success;
    }

    @Override
    public List<LoanDetailDto> getAllLoanDetails() {

         List<LoanDetailDto> dtos = this.loanDetailRepository.findAll()
                 .stream()
                    .map(l -> modelMapper.map(l,LoanDetailDto.class)).collect(Collectors.toList());
         return  dtos;
    }

    @Override
    public LoanDetailDto getLoanDetailsById(Long id) {
        return mapToDTO(this.loanDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Loan Details","ID",id)));
    }

    @Override
    public LoanDetailDto updateLoanDetails(Long id, LoanDetailDtoFromClient loanDetailDtoFromClient) {

        LoanDetail loan =  this.loanDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan Details","ID",id));

        loan.setLoanAmount(loanDetailDtoFromClient.getLoanAmount());
        loan.setInterestRate(loanDetailDtoFromClient.getInterestRate());
        loan.setPaymentFrequency(loanDetailDtoFromClient.getPaymentFrequency());
        loan.setNumberOfPayments(loanDetailDtoFromClient.getNumberOfPayments());

        loan.setPayments(new ArrayList<>());
        loan.setPaymentDetail(new PaymentDetail());


        loan = setPaymentDetailAndAmortizationPaymentSchedule(loan);


        return mapToDTO(this.loanDetailRepository.save(loan));


    }


    private  LoanDetail setPaymentDetailAndAmortizationPaymentSchedule(LoanDetail loan){

        loan = PaymentDetails.create(loan);

        loan = AmortizationPaymentSchedule.create(loan);

        return loan;

    }



    //convert entity to dto
    private LoanDetailDto mapToDTO(LoanDetail loanDetail){
        LoanDetailDto loanDetailDto = modelMapper.map(loanDetail,LoanDetailDto.class);
        return loanDetailDto;
    }

    // convert dto to entity
    private LoanDetail mapToEntity(LoanDetailDtoFromClient loanDetailDto){
        LoanDetail loanDetail =  modelMapper.map(loanDetailDto,LoanDetail.class);
        return loanDetail;
    }

}
