/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle;

import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleMiniCategory.MOTOBIKE;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleMiniCategory.PASSANGER_CAR;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleMiniCategory.PASSANGER_SUB_5_TONNER;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleMiniCategory.QUADBIKE;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleMiniCategory.SCOOTER;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleSubCategory.L;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleSubCategory.M;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleSubCategory.N;
import static com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleSubCategory.O;

/**
 *
 * @author stepin
 */

public enum VehicleCategory {
    
    //L
    L1(L, SCOOTER),
    L2(L, SCOOTER),
    
    L3(L, MOTOBIKE),
    L4(L, MOTOBIKE),
    L5(L, MOTOBIKE),
    
    L6(L, QUADBIKE),
    L7(L, QUADBIKE),
    
    //M
    M1(M, PASSANGER_CAR),
    M2(M, PASSANGER_SUB_5_TONNER),
    
    //N
    N1(N, null),
    N2(N, null),
    N3(N, null),
    
    //O
    O1(O, null),
    O2(O, null),
    O3(O, null);

    
    private final String fullName;
    private final VehicleSubCategory subCategory;
    private final VehicleMiniCategory miniCategory;
    
    VehicleCategory(VehicleSubCategory subCategory, VehicleMiniCategory miniCategory){
        this.subCategory = subCategory;
        this.miniCategory = miniCategory;
        
        this.fullName 
                = this.name() 
                + " - " + subCategory.getFullName() 
                + "." + (miniCategory != null ? miniCategory.getFullName() : "");
    }

    public String getFullName() {
        return fullName;
    }

    public VehicleSubCategory getSubCategory() {
        return subCategory;
    }

    public VehicleMiniCategory getMiniCategory() {
        return miniCategory;
    }
    
}
