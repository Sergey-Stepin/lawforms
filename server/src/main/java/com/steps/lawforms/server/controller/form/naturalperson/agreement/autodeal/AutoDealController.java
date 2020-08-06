/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.controller.form.naturalperson.agreement.autodeal;

import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.AutoDeal;
import com.steps.lawforms.server.model.naturalperson.agreement.autodeal.PersonDetails;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author stepin
 */
@Controller
@RequestMapping("form/naturalperson/agreement/autodeal")
@SessionAttributes({"formParams", "deal", "fragmentPath", "formName"})
public class AutoDealController {

    private final static Logger LOGGER = LogManager.getLogger();

    @ModelAttribute("deal")
    public AutoDeal deal() {
        return new AutoDeal();
    }

    @ModelAttribute("seller")
    public PersonDetails seller() {
        return new PersonDetails();
    }

    @ModelAttribute("buyer")
    public PersonDetails buyer() {
        return new PersonDetails();
    }
    
    @ModelAttribute("vehicleTechData")
    public VehicleTechData vehicleTechData() {
        return new VehicleTechData();
    }

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
    public String postVehicleParamsPrev() {
        
        return "redirect:/form-categories/aggreements.html";
    }

    @PostMapping(value = "/vehicle_params", params = {"_next"})
    public String postVehicleParamsNext(
            @Valid @ModelAttribute("vehicleTechData") VehicleTechData vehicleTechData,
            Errors errors,
            @ModelAttribute("deal") AutoDeal deal,
            BindingResult result) {

        if (errors.hasErrors()) {
            return "form/naturalperson/agreement/autodeal/vehicle_params";
        }

        deal.setVehicleTechData(vehicleTechData);

        return "form/naturalperson/agreement/autodeal/seller_params";
    }

    @PostMapping(value = "/seller_params", params = {"_prev"})
    public String postSellerParamsPrev() {
        return "form/naturalperson/agreement/autodeal/vehicle_params";
    }

    @PostMapping(value = "/seller_params", params = {"_next"})
    public String postSellerParamsNext(
            @Valid @ModelAttribute("seller") PersonDetails personDetails,
            Errors errors,
            @ModelAttribute("deal") AutoDeal deal) {

        if (errors.hasErrors()) {
            return "form/naturalperson/agreement/autodeal/seller_params";
        }

        deal.setSeller(personDetails.getPerson());
        deal.setSellerPassport(personDetails.getPersonPassport());

        return "form/naturalperson/agreement/autodeal/buyer_params";
    }

    @PostMapping(value = "/buyer_params", params = {"_prev"})
    public String postBuyerParamsPrev() {
        return "form/naturalperson/agreement/autodeal/seller_params";
    }

    @PostMapping(value = "/buyer_params", params = {"_next"})
    public String postBuyerParamsNext(
            @Valid @ModelAttribute("buyer") PersonDetails personDetails,
            Errors errors,
            @ModelAttribute("deal") AutoDeal deal) {

        if (errors.hasErrors()) {
            return "form/naturalperson/agreement/autodeal/buyer_params";
        }

        deal.setBuyer(personDetails.getPerson());
        deal.setBuyerPassport(personDetails.getPersonPassport());

        return "form/naturalperson/agreement/autodeal/general_terms";
    }

    @PostMapping(value = "/general_terms", params = {"_prev"})
    public String postGeneralTermsPrev() {
        return "form/naturalperson/agreement/autodeal/buyer_params";
    }

    @PostMapping(value = "/general_terms", params = {"_next"})
    public String postGeneralTermsNext(
            @Valid @ModelAttribute("deal") AutoDeal deal,
            Errors errors) {

        if (errors.hasErrors()) {
            return "form/naturalperson/agreement/autodeal/general_terms";
        }
        
        return "form/naturalperson/agreement/autodeal/immidiate_terms";
    }

    @PostMapping(value = "/immidiate_terms", params = {"_prev"})
    public String postImmidiateTermsPrev() {
        return "form/naturalperson/agreement/autodeal/general_terms";
    }

    @PostMapping(value = "/immidiate_terms", params = {"_next"})
    public String postImmidiateTermsNext(
            Model model,
            @ModelAttribute("deal") AutoDeal deal) {

        LOGGER.debug("### AutoDeal deal=" + deal);

        if (!deal.isImmidiateTransfer()) {
            return "form/naturalperson/agreement/autodeal/transfer_terms";

        } else if (!deal.isImmidiatePayment()) {
            return "form/naturalperson/agreement/autodeal/payment_terms";

        } else {
            return postResult(model, deal);
        }

    }

    @PostMapping(value = "/transfer_terms", params = {"_prev"})
    public String postTransferTermsPrev() {
        return "form/naturalperson/agreement/autodeal/immidiate_terms";
    }

    @PostMapping(value = "/transfer_terms", params = {"_next"})
    public String postTransferTermsNext(
            Model model,
            @ModelAttribute("deal") AutoDeal deal) {

        if (!deal.isImmidiatePayment()) {
            return "form/naturalperson/agreement/autodeal/payment_terms";

        } else {
            return postResult(model, deal);
        }

    }

    @PostMapping(value = "/payment_terms", params = {"_prev"})
    public String postPaymentTermsPrev() {
        return "form/naturalperson/agreement/autodeal/transfer_terms";
    }

    @PostMapping(value = "/payment_terms", params = {"_next"})
    public String postPaymentTermsNext(
            Model model,
            @ModelAttribute("deal") AutoDeal deal) {

        return postResult(model, deal);

    }

    private String postResult(Model model, AutoDeal deal) {
        model.addAttribute("formParams", deal);
        model.addAttribute("formName", new String("AutoDeal"));
        model.addAttribute("fragmentPath", "form/naturalperson/agreement/autodeal/autodeal_template");

        return "form-frame";
    }
}
