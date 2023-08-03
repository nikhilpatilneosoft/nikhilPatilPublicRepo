package com.example.Security.auth;

import com.example.Security.auth.DTOs.AuthenticationRequset;
import com.example.Security.auth.DTOs.AuthenticationResponses;
import com.example.Security.auth.DTOs.RegisterRequset;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponses> register(
            @RequestBody RegisterRequset requset
    )
    {
        return ResponseEntity.ok(authenticationService.register(requset));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponses> authenticate(
            @RequestBody AuthenticationRequset request
    )
    {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/users")
    public String getUser()
    {
        return "found";
    }
}
