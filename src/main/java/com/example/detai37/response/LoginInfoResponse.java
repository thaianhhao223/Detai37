package com.example.detai37.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginInfoResponse {
    private String userName;
    private String userId;
    private String userType;
}
