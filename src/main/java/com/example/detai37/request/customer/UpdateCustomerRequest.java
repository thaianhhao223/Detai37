package com.example.detai37.request.customer;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UpdateCustomerRequest implements Serializable {
    @NotBlank
    @NotNull
    private String id;
    @NotBlank
    @NotNull
    private String firstName;
    @NotBlank
    @NotNull
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    private String address;
    private String urlImage;
    private Date birthDate;
    private Integer status;
}
