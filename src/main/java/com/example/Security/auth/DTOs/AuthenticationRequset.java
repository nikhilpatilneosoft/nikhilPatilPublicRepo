package com.example.Security.auth.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationRequset {

    private String email;
    private String password;
}
