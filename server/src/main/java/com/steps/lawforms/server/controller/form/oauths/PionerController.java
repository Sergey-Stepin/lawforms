/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller.form.oauths;

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
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.thymeleaf.context.Context;

/**
 *
 * @author stepin
 */

@Controller
@RequestMapping("form/oauths/pioner")
@SessionAttributes({"formParams", "fragmentPath", "formName"})
public class PionerController {

    @Autowired
    private PdfService templateToPdfService;

    private final static Logger LOGGER = LogManager.getLogger();

    @ModelAttribute("availableBirthPlaces")
    public List<BirthPlace> getBirthPlaceList() {
        return Stream.of(BirthPlace.values())
                .collect(Collectors.toList());
    }

    @GetMapping
    public String getParameters(Model model) {
        model.addAttribute("formParams", new ProbForm());
        return "form/oauths/pioner/parameters";
    }

    @PostMapping(params = {"_ok"})
    public String postParameters(
            @Valid ProbForm formParams,
            Errors errors,
            HttpServletResponse response,
            Model model) throws IOException {

        if (errors.hasErrors()) {
            return "form/oauths/pioner/parameters";
        }

        prepareFromParams(formParams);
        
        //String pictureData = Base64Utils.encodeToString(formParams.getPicture().getBytes());
        //formParams.setPictureData(Base64Utils.encodeToString(formParams.getPicture().getBytes()));

//        String name = formParams.getLastName() + " "
//                + formParams.getFirstName() + " "
//                + formParams.getMiddleName();

//        model.addAttribute("name", name);
//        model.addAttribute("birthPlace", formParams.getBirthPlace().getRusName());
//        model.addAttribute("birthday", formParams.getBirthday());
//        model.addAttribute("today", LocalDate.now());
//        model.addAttribute("amount", formParams.getAmount());
//        model.addAttribute("pictureData", pictureData);

        model.addAttribute("formParams", formParams);
        model.addAttribute("formName", new String("PionerOath"));
        model.addAttribute("fragmentPath", "form/oauths/pioner/pioner_oath_template");
        
        response.addHeader("Cache-Control", "no cache");
        
        return "form-frame";
    }

//    @PostMapping(params = {"_pdfview"})
//    public PdfView generateFile(
//            @ModelAttribute("formParams") ProbForm formParams,
//            Model model) throws IOException {
//
//        prepareFromParams(formParams);
//        
//        Context context = new Context();
////        context.setVariable("name", name);
////        context.setVariable("birthPlace", application.getBirthPlace().getRusName());
////        context.setVariable("birthday", application.getBirthday());
////        context.setVariable("today", LocalDate.now());
////        context.setVariable("amount", application.getAmount());
////        context.setVariable("pictureData", pictureData);
//
//        context.setVariable("formParams", formParams);
//
//        return new PdfView(templateToPdfService.prepareByteArrayOutputStream("form/oauths/pioner/pioner_oath_template", context));
//    }
    
    private void prepareFromParams(ProbForm formParams) throws IOException{
        formParams.setPictureData(Base64Utils.encodeToString(formParams.getPicture().getBytes()));
    }
}
