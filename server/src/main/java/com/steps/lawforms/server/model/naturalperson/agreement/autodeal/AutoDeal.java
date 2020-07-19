/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal;

import com.steps.lawforms.server.model.common.Passport;
import com.steps.lawforms.server.model.common.Person;
import com.steps.lawforms.server.model.iFormParameters;
import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleTechData;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author stepin
 */

@Data
public class AutoDeal implements iFormParameters, Serializable{
    
    private static final DateTimeFormatter DAY_MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    private String venue;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dealDate = LocalDate.now();
    
    private double price;
    private double priceText;
    
    private Person seller;
    
    private Passport sellerPassport;
    
    private Person buyer;
    private Passport buyerPassport;
    
    private VehicleTechData vehicleTechData;

    private boolean immidiateTransfer;
    
    private int daysForTransfer; 
    
    private boolean immidiatePayment;
    private int daysForPayment; 
    
    public String formattedDealDate(){
        return DAY_MONTH_YEAR_FORMATTER.format(dealDate);
    }
}
