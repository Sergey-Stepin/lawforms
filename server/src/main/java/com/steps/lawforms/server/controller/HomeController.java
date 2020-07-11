/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author stepin
 */

@Controller
public class HomeController {
    
    @GetMapping({"/", "/home"})
    public String getHome(){
        return "home";
    }
    
}
