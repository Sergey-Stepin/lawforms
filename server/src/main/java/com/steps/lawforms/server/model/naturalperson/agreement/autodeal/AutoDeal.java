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
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author stepin
 */

@Data
public class AutoDeal implements iFormParameters, Serializable{
    
    public static final NumberFormat PRICE_FORMAT = NumberFormat.getInstance(Locale.forLanguageTag("ru-RU"));
    
    static {
        PRICE_FORMAT.setGroupingUsed(true);
    }
    
    private static final DateTimeFormatter DAY_MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    @NotEmpty(message = "Незаполено поле")
    private String venue;
    
    @NotNull(message = "Незаполено поле")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dealDate = LocalDate.now();

    @Positive(message = "Значение должно быть больше 0.0")
    private double price;
    
    private String priceText;

    @NotNull
    private Person seller;
    
    @NotNull
    private Passport sellerPassport;
    
    @NotNull
    private Person buyer;
    
    @NotNull
    private Passport buyerPassport;
    
    @NotNull
    private VehicleTechData vehicleTechData;

    private boolean immidiateTransfer;
    private int daysForTransfer; 
    
    private boolean immidiatePayment;
    private int daysForPayment; 
    
    public String formattedDealDate(){
        return DAY_MONTH_YEAR_FORMATTER.format(dealDate);
    }
    
    public String formattedPrice(){
        return PRICE_FORMAT.format(price);
    }
}
