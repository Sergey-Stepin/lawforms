/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.vew;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.view.AbstractView;


/**
 *
 * @author stepin
 */
public class PdfView extends AbstractView {

    private final ByteArrayOutputStream baos;

    public PdfView(ByteArrayOutputStream baos) {

        this.setContentType("application/pdf");
        this.baos = baos;
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(
            Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

            writeToResponse(response, baos);
    }
}
