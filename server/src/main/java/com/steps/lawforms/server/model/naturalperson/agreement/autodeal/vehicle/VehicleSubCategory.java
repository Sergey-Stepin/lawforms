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

public enum VehicleSubCategory {
    L("Мототранспортные средства"),
    M("Пассажирские транспортные средства"),
    N("Грузовые автомобили"),
    O("Прицепы");
        
    private final String fullName;
    
    VehicleSubCategory(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
