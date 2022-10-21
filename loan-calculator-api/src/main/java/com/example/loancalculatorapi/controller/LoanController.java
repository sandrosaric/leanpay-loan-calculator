package com.example.loancalculatorapi.controller;

import com.example.loancalculatorapi.dto.LoanDetailDto;
import com.example.loancalculatorapi.dto.LoanDetailDtoFromClient;
import com.example.loancalculatorapi.exception.ErrorDetails;
import com.example.loancalculatorapi.service.LoanDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/loans")
public class LoanController {

    private final LoanDetailService loanDetailService;


    public LoanController(LoanDetailService loanDetailService) {
        this.loanDetailService = loanDetailService;

    }

    //build api for saving new resource
    @PostMapping()
    public ResponseEntity<?> saveLoanDetails(@Valid @RequestBody LoanDetailDtoFromClient loanDetailDtoFromClient,BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ErrorDetails errorDetails = new ErrorDetails(new Date(),"Validation Error",bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<LoanDetailDto>(this.loanDetailService.saveLoanDetails(loanDetailDtoFromClient), HttpStatus.CREATED);
    }

    //build api to delete resource by id

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLoanDetails(@PathVariable Long id){
        this.loanDetailService.deleteLoanDetailsById(id);
        return new ResponseEntity<String>("The resource with id: " + id + "has just been deleted",HttpStatus.OK);
    }

    //build api to get all resources from db
    @GetMapping
    public ResponseEntity<List<LoanDetailDto>> getAllLoanDetails(){
        return new ResponseEntity<List<LoanDetailDto>>(this.loanDetailService.getAllLoanDetails(),HttpStatus.OK);

    }

    //build an api for retrieving one resource by its id
    @GetMapping("{id}")
    public ResponseEntity<LoanDetailDto> getLoanDetailsById(@PathVariable Long id){
        return new ResponseEntity<LoanDetailDto>(this.loanDetailService.getLoanDetailsById(id),HttpStatus.OK);
    }

    //build an api to update one resource or save new one if it doesnt exists
    @PutMapping("{id}")
    public ResponseEntity<LoanDetailDto> updateLoanDetails(@PathVariable Long id, @Valid @RequestBody LoanDetailDtoFromClient loanDetailDtoFromClient){
        return new ResponseEntity<LoanDetailDto>(this.loanDetailService.updateLoanDetails(id,loanDetailDtoFromClient),HttpStatus.OK);
    }



}
