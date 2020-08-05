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
import java.io.IOException;
import java.util.List;
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
import org.springframework.web.bind.support.SessionStatus;

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
    
    @ModelAttribute("probForm")
    public ProbForm probForm(){
        return new ProbForm();
    }

    @GetMapping
    public String getParameters() {
        return "form/oauths/pioner/parameters";
    }

    @PostMapping(params = {"_ok"})
    public String postParameters(
            @Valid @ModelAttribute("probForm") ProbForm probForm,
            Errors errors,
            HttpServletResponse response,
            Model model) throws IOException {

        if (errors.hasErrors()) {
            return "form/oauths/pioner/parameters";
        }

        prepareFromParams(probForm);
        
        model.addAttribute("formParams", probForm);
        model.addAttribute("formName", new String("PionerOath"));
        model.addAttribute("fragmentPath", "form/oauths/pioner/pioner_oath_template");
        
        response.addHeader("Cache-Control", "no cache");
        
        return "form-frame";
    }

    private void prepareFromParams(ProbForm probForm) throws IOException{
        probForm.setPictureData(Base64Utils.encodeToString(probForm.getPicture().getBytes()));
    }
}
