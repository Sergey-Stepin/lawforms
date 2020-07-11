/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steps.lawforms.server.model;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 *
 * @author stepin
 */
@Data
public class ProbForm implements Serializable {

    @NotBlank(message = "Незаполено поле <Имя>")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Незаполено поле <Фамилия>")
    private String lastName;

    private MultipartFile picture;

    @NotNull(message = "Незаполено поле <Когда>")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull(message = "Незаполено поле <Где>")
    private BirthPlace birthPlace;

    @Positive(message = "Число должно быть больше 0")
    private double amount;

}
