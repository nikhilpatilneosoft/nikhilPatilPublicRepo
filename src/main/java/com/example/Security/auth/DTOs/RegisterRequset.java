package com.example.Security.auth.DTOs;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequset {

    private String firstName;
    private String lastName;
    private  String email;
    private String password;
}
