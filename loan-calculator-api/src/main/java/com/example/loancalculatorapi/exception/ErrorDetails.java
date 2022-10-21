package com.example.loancalculatorapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

//send custom error details to the client
@Data
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
}
