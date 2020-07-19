/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author stepin
 */

@Data
public class Passport {
    
    private static final DateTimeFormatter DAY_MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");    
    
    private String firstSerialNum;
    
    private String secondSerialNum;
    
    private String number;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;
    
    private String issuer;
    
    private String issuerDepartmentCode;
    
    private String currentAddress;
    
    public String getFullPassportNum(){
        return firstSerialNum + " " + secondSerialNum + " " + number;
    }
    
    public String formattedIssued(){
        return DAY_MONTH_YEAR_FORMATTER.format(issued);
    }
    
}
