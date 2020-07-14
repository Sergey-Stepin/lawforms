/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author stepin
 */

@Controller
public class FragmentsController {
    
    @GetMapping("/fragments")
    public String getHome() {
        return "fragments/fragments.html";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        
        model.addAttribute("paramPath", "fragments/general.html");
        return "fragments/test.html";
    }
    
}
