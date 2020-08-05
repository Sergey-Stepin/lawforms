/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle;

import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

/**
 *
 * @author stepin
 */

@Data
public class VehicleTechData {
    
    @NotEmpty(message = "Незаполено поле")
    private String model;
    
    @NotEmpty(message = "Незаполено поле")
    private String numberOfStateRegistration;
    
    @NotEmpty(message = "Незаполено поле")
    private String vin;
    
    @NotEmpty(message = "Незаполено поле")
    private String name;

    @NotNull(message = "Незаполено поле")
    private VehicleCategory category;
    
    @Min(value = 1900, message = "Значение не может быть меньше 1900")
    private int yearOfIssuing = LocalDate.now().getYear();

    @NotEmpty(message = "Незаполено поле")
    private String engineModel;
    
    @NotEmpty(message = "Незаполено поле")
    private String enginePower;
    
    @Positive(message = "Значение должно быть больше 0")
    private double engineVolume;
    
    @NotEmpty(message = "Незаполено поле")
    private String engineType;

    @NotEmpty(message = "Незаполено поле")
    private String chassis;
    
    @NotEmpty(message = "Незаполено поле")
    private String bodyNumber;
    
    @NotEmpty(message = "Незаполено поле")
    private String bodyColor;
    
    @NotEmpty(message = "Незаполено поле")
    private String vehiclePassport;
    
    @NotEmpty(message = "Незаполено поле")
    private String vehiclePassportIssuer;
 
}
