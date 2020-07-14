/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller.form;

import com.steps.lawforms.server.model.ProbForm;
import com.steps.lawforms.server.service.PdfService;
import com.steps.lawforms.server.vew.PdfView;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.thymeleaf.context.Context;

/**
 *
 * @author stepin
 */
@Controller
@RequestMapping("form/frame")
@SessionAttributes({"formParams", "fragmentPath", "formName"})
public class FormFrameController {

    @Autowired
    private PdfService templateToPdfService;

    private final static Logger LOGGER = LogManager.getLogger();

    @PostMapping(params = {"_pdfview"})
    public PdfView viewPdfFile(
            @ModelAttribute("formParams") ProbForm formParams,
            @ModelAttribute("fragmentPath") String fragmentPath,
            HttpServletResponse response,
            //SessionStatus status,
            Model model) throws IOException {

        //status.setComplete();

        LOGGER.debug("### fragmentPath=" + fragmentPath);
        LOGGER.debug("### formParams=" + formParams);

        Context context = new Context();

        context.setVariable("formParams", formParams);

        response.addHeader("Cache-Control", "no cache");
        
        return new PdfView(templateToPdfService.prepareByteArrayOutputStream(fragmentPath, context));
    }

    @PostMapping(params = {"_pdfdownload"})
    public ResponseEntity<StreamingResponseBody> downloadPdfFile(
            @ModelAttribute("formParams") ProbForm formParams,
            @ModelAttribute("formName") String formName,
            @ModelAttribute("fragmentPath") String fragmentPath,
            //SessionStatus status,
            Model model) throws IOException {

        //status.setComplete();

        LOGGER.debug("### fragmentPath=" + fragmentPath);
        LOGGER.debug("### formParams=" + formParams);

        Context context = new Context();
        context.setVariable("formParams", formParams);

        StreamingResponseBody stream = out -> {
            byte[] fileAsByteArray = templateToPdfService.prepareByteArrayOutputStream(fragmentPath, context).toByteArray();
            out.write(fileAsByteArray);
        };
        
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf("application/force-download"))
                .header("Content-Disposition", "attachment; filename=" + formName + ".pdf")
                .body(stream);
    }
}
