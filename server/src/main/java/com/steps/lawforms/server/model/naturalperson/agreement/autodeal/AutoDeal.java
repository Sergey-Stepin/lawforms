/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal;

import com.steps.lawforms.server.model.common.Passport;
import com.steps.lawforms.server.model.common.Person;
import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleTechData;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author stepin
 */

@Data
public class AutoDeal {
    
    private String venue;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dealDate;
    
    private double price;
    private String priceText;

    private Person seller;
    private Passport sellPassport;
    
    private Person buyer;
    private Passport buyerPassport;
    
    private VehicleTechData vehicleTechData;
    
    private boolean immidiateTransfer;
    private int daysForTransfer; 
    
    private boolean immidiatePayment;
    private int daysForPayment; 
    
    
}
