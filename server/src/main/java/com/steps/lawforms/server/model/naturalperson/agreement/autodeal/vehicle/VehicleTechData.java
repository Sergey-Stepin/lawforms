/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle;

import lombok.Data;

/**
 *
 * @author stepin
 */

@Data
public class VehicleTechData {
    
    private String model;
    private String numberOfStateRegistration;
    private String vin;
    private String name;
    private VehicleCategory category;
    private int yearOfIssuing;

    private String engineModel;
    private String enginePower;
    private double engineVolume;
    private String engineType;

    private String chassis;
    private String bodyNumber;
    private String bodyColor;
    
    private String vehiclePassport;
    private String vehiclePassportIssuer;
 
}
