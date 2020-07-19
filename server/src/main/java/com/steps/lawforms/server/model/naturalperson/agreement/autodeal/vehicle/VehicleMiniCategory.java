/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle;

/**
 *
 * @author stepin
 */
public enum VehicleMiniCategory {
    
    //L
    SCOOTER("Мопеды, мотовелосипеды, мокики"),
    MOTOBIKE("Мотоциклы, мотороллеры, трициклы"),
    QUADBIKE("Квадрициклы"),
    
    //M
    PASSANGER_CAR("Автомобили легковые"),
    PASSANGER_SUB_5_TONNER("Автобусы, троллейбусы, специализированные пассажирские транспортные средства");
    
    //N - no mini=categories
    
    //O - no mini=categories 
    
    private final String fullName;
    
    VehicleMiniCategory(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
