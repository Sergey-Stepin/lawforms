/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.service;

//import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.layout.font.FontProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author stepin
 */
@Service
public class PdfService {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int OUTPUT_BYTE_ARRAY_INITIAL_SIZE = 4096;

    @Autowired
    private TemplateEngine templateEngine;

    public String parseThymeleafTemplate(String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }

    public void writeHtmlToPdf(String html, OutputStream pdfOutputStream) throws IOException {
        ConverterProperties converterProperties = new ConverterProperties();
        FontProvider fontProvider = new DefaultFontProvider();
        converterProperties.setFontProvider(fontProvider);

        HtmlConverter.convertToPdf(html, pdfOutputStream);
    }

    public ByteArrayOutputStream prepareByteArrayOutputStream(String templateName, Context context) {
        try (ByteArrayOutputStream baos = createTemporaryOutputStream()) {
        
            String html = this.parseThymeleafTemplate(templateName, context);
            LOGGER.debug("HTML length=" + html.length());

            writeHtmlToPdf(html, baos);
            return baos;

        } catch (IOException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    private ByteArrayOutputStream createTemporaryOutputStream() {
        return new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
    }

}
