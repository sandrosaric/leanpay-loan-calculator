package com.example.loancalculatorapi.model;

//enumeration for chosen payment frequency that is coming for the client
//its also going to register in db and help in some calculations
public enum PaymentFrequency {
    DAILY,WEEKLY,BIWEEKLY,SEMI_MONTH,MONTHLY,BIMONTHLY,QUARTERLY,SEMI_ANNUAL,ANNUAL;
}
