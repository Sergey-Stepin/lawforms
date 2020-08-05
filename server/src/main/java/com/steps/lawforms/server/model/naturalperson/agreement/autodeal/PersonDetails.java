/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model.naturalperson.agreement.autodeal;

import com.steps.lawforms.server.model.common.Passport;
import com.steps.lawforms.server.model.common.Person;
import javax.validation.Valid;
import lombok.Data;

/**
 *
 * @author stepin
 */
@Data
public class PersonDetails {
    
    @Valid
    private Person person;
    
    @Valid
    private Passport personPassport;
    
}
