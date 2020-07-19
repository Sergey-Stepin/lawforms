/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller.form.naturalperson.agreement.autodeal;

import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.AutoDeal;
import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleCategory;
import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.vehicle.VehicleTechData;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("form/naturalperson/agreement/autodeal")
@SessionAttributes({"formParams", "fragmentPath", "formName"})
public class AutoDealController {

    private final static Logger LOGGER = LogManager.getLogger();

    @ModelAttribute("formParams")
    public AutoDeal formParams() {
        return new AutoDeal();
    }
    
//    @ModelAttribute("vehicleTechData")
//    public VehicleTechData vehicleTechData() {
//        return new VehicleTechData();
//    }

    @ModelAttribute("categories")
    public List<VehicleCategory> vehicleCategory() {
        return Stream.of(VehicleCategory.values())
                .collect(Collectors.toList());
    }

    @GetMapping
    public String getVehicleParams() {
        return "form/naturalperson/agreement/autodeal/vehicle_params";
    }

    @PostMapping(value = "/vehicle_params", params = {"_prev"})
    public String postVehicleParamsPrev(SessionStatus status) {
        status.setComplete();
        return "redirect:/form-categories/aggreements.html";
    }

    @PostMapping(value = "/vehicle_params", params = {"_next"})
    public String postVehicleParamsNext(
            //@ModelAttribute("formParams") AutoDeal formParams,
            //@Valid @ModelAttribute("vehicleTechData") VehicleTechData vehicleTechData,
            //Errors errors,
            @ModelAttribute("formParams") AutoDeal formParams,
            BindingResult result) {

//        if (errors.hasErrors()) {
//            return "form/naturalperson/agreement/autodeal/vehicle_params";
//        }
        
        return "form/naturalperson/agreement/autodeal/seller_params";
    }

    @PostMapping(value = "/seller_params", params = {"_prev"})
    public String postSellerParamsPrev() {
        return "form/naturalperson/agreement/autodeal/vehicle_params";
    }

    @PostMapping(value = "/seller_params", params = {"_next"})
    public String postSellerParamsNext(
            @ModelAttribute("formParams") AutoDeal formParams) {

        return "form/naturalperson/agreement/autodeal/buyer_params";
    }

    @PostMapping(value = "/buyer_params", params = {"_prev"})
    public String postBuyerParamsPrev() {
        return "form/naturalperson/agreement/autodeal/seller_params";
    }

    @PostMapping(value = "/buyer_params", params = {"_next"})
    public String postBuyerParamsNext(
            @ModelAttribute("formParams") AutoDeal formParams) {

        return "form/naturalperson/agreement/autodeal/general_terms";
    }

    @PostMapping(value = "/general_terms", params = {"_prev"})
    public String postGeneralTermsPrev() {
        return "form/naturalperson/agreement/autodeal/buyer_params";
    }

    @PostMapping(value = "/general_terms", params = {"_next"})
    public String postGeneralTermsNext(
            @ModelAttribute("formParams") AutoDeal formParams) {

        return "form/naturalperson/agreement/autodeal/immidiate_terms";
    }

    @PostMapping(value = "/immidiate_terms", params = {"_prev"})
    public String postImmidiateTermsPrev() {
        return "form/naturalperson/agreement/autodeal/general_terms";
    }

    @PostMapping(value = "/immidiate_terms", params = {"_next"})
    public String postImmidiateTermsNext(
            Model model,
            @ModelAttribute("formParams") AutoDeal formParams) {

        System.out.println("### AutoDeal formParams=" + formParams);

        if (!formParams.isImmidiateTransfer()) {
            return "form/naturalperson/agreement/autodeal/transfer_terms";

        } else if (!formParams.isImmidiatePayment()) {
            return "form/naturalperson/agreement/autodeal/payment_terms";

        } else {
            return postResult(model);
        }

    }

    @PostMapping(value = "/transfer_terms", params = {"_prev"})
    public String postTransferTermsPrev() {
        return "form/naturalperson/agreement/autodeal/immidiate_terms";
    }

    @PostMapping(value = "/transfer_terms", params = {"_next"})
    public String postTransferTermsNext(
            Model model,
            @ModelAttribute("formParams") AutoDeal formParams) {

        if (!formParams.isImmidiatePayment()) {
            return "form/naturalperson/agreement/autodeal/payment_terms";

        } else {
            return postResult(model);
        }

    }

    @PostMapping(value = "/payment_terms", params = {"_prev"})
    public String postPaymentTermsPrev() {
        return "form/naturalperson/agreement/autodeal/transfer_terms";
    }

    @PostMapping(value = "/payment_terms", params = {"_next"})
    public String postPaymentTermsNext(
            Model model,
            @ModelAttribute("formParams") AutoDeal formParams) {

        return postResult(model);

    }

    private String postResult(Model model) {
        model.addAttribute("formName", new String("AutoDeal"));
        model.addAttribute("fragmentPath", "form/naturalperson/agreement/autodeal/autodeal_template");

        return "form-frame";
    }
}
