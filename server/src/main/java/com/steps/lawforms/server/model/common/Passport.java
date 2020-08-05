/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
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
    
    @NotEmpty(message = "Незаполено поле")
    private String number;
    
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;
    
    @NotEmpty(message = "Незаполено поле")
    private String issuer;
    
    @NotEmpty(message = "Незаполено поле")
    private String issuerDepartmentCode;
    
    @NotEmpty(message = "Незаполено поле")
    private String currentAddress;
    
    public String getFullPassportNum(){
        return firstSerialNum + " " + secondSerialNum + " " + number;
    }
    
    public String formattedIssued(){
        return DAY_MONTH_YEAR_FORMATTER.format(issued);
    }
    
}
