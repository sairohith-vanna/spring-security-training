package com.experimental.security.controllers;

import com.experimental.security.models.AuthUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @GetMapping("login")
    public String authenticate(@RequestBody AuthUser user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        if(authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "Authenticated.";
        }
        else {
            return "Credentials do not match.";
        }
    }
}