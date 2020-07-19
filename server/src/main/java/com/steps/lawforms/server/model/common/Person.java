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
public class Person {
    
    private static final DateTimeFormatter DAY_MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    private String firstName;
    private String middleName;    
    private String lastName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday; 
    
    public String getFIO(){
        return lastName + " " + firstName + " " + middleName;
    }

    public String getShortFIO(){
        return lastName + " " + firstName.charAt(0) + "." + middleName.charAt(0) + ".";
    }
    
    public String formattedBirthday(){
        return DAY_MONTH_YEAR_FORMATTER.format(birthday);
    }
}
