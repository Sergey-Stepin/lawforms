/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller;

//import com.itextpdf.text.DocumentException;
import com.steps.lawforms.server.model.BirthPlace;
import com.steps.lawforms.server.model.ProbForm;
import com.steps.lawforms.server.service.PdfService;
import com.steps.lawforms.server.vew.PdfView;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;

/**
 *
 * @author stepin
 */

@Controller
@RequestMapping("form/prob")
public class ProbController {

    @Autowired
    private PdfService templateToPdfService;

    private final static Logger LOGGER = LogManager.getLogger();

    @ModelAttribute("availableBirthPlaces")
    public List<BirthPlace> getBirthPlaceList() {
        return Stream.of(BirthPlace.values())
                .collect(Collectors.toList());
    }

    @GetMapping
    public String getApplication(Model model) {
        model.addAttribute("probForm", new ProbForm());
        return "form/prob";
    }

    @PostMapping(params = {"_ok"})
    public String postApplication(
            @Valid ProbForm application,
            Errors errors,
            Model model) throws IOException {

        if (errors.hasErrors()) {
            return "form/prob";
        }

        String pictureData = Base64Utils.encodeToString(application.getPicture().getBytes());

        String name = application.getLastName() + " "
                + application.getFirstName() + " "
                + application.getMiddleName();

        model.addAttribute("name", name);
        model.addAttribute("birthPlace", application.getBirthPlace().getRusName());
        model.addAttribute("birthday", application.getBirthday());
        model.addAttribute("today", LocalDate.now());
        model.addAttribute("amount", application.getAmount());
        model.addAttribute("pictureData", pictureData);

        return "form/oath";
    }

    @PostMapping(params = {"_pdfview"})
    public PdfView generateFile(
            @ModelAttribute("probForm") ProbForm application,
            Model model) throws IOException {

        String pictureData = Base64Utils.encodeToString(application.getPicture().getBytes());

        String name = application.getLastName() + " "
                + application.getFirstName() + " "
                + application.getMiddleName();

        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("birthPlace", application.getBirthPlace().getRusName());
        context.setVariable("birthday", application.getBirthday());
        context.setVariable("today", LocalDate.now());
        context.setVariable("amount", application.getAmount());
        context.setVariable("pictureData", pictureData);

        return new PdfView(templateToPdfService.prepareByteArrayOutputStream("form/oath", context));
    }

}
