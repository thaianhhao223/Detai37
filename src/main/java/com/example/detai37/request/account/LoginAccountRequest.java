package com.example.detai37.request.account;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginAccountRequest {
    @NotNull
    @NotBlank
    private String userName;
    @NotNull
    @NotBlank
    private String passWord;
}
