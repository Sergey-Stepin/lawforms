/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.common;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author stepin
 */

@Data
public class Passport {
    
    private String firstSerialNum;
    
    private String secondSerialNum;
    
    private String number;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date issued;
    
    private String issuer;
    
    private String issuerDepartmentCode;
    
    private String currentAddress;
}
