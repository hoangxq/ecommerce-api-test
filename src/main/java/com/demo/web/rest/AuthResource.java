package com.demo.web.rest;

import com.demo.service.AuthService;
import com.demo.web.dto.request.LoginRequest;
import com.demo.web.dto.request.SignupRequest;
import com.demo.web.dto.response.utils.ErrorResponse;
import com.demo.web.dto.response.utils.Response;
import com.demo.web.dto.response.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthResource {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<Response> authenticateUser (@Valid @RequestBody LoginRequest loginRequest){
        return ResponseUtils.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<Response> registerUser (@Valid @RequestBody SignupRequest signupRequest){
        if (authService.checkUser(signupRequest.getUsername()))
            return ResponseUtils.badRequest(new ErrorResponse(null, "Username or email is already taken !", null));
        authService.registerUser(signupRequest);
        return ResponseUtils.created();
    }

}
