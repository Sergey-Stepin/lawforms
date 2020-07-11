/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model;

/**
 *
 * @author stepin
 */
public enum BirthPlace {
    
    MOSCOW("город-герой Москвa"),
    PARIS("город-герое Париж"),
    NAZARIEVO("д. Назарьево Рязанской губернии"),
    KIEV("стольнiй град Київ");
    
    private final String rusName;
    
    private BirthPlace(String rusName){
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}
