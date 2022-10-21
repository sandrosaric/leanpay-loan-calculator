package com.example.loancalculatorapi.service;

import com.example.loancalculatorapi.dto.LoanDetailDto;
import com.example.loancalculatorapi.dto.LoanDetailDtoFromClient;

import java.util.List;

//interface for the main service business methods
public interface LoanDetailService {

    LoanDetailDto saveLoanDetails(LoanDetailDtoFromClient loanDetail);

    boolean deleteLoanDetailsById(Long id);


    List<LoanDetailDto> getAllLoanDetails();
    LoanDetailDto getLoanDetailsById(Long id);

    LoanDetailDto updateLoanDetails(Long id, LoanDetailDtoFromClient loanDetailDtoFromClient);
}
