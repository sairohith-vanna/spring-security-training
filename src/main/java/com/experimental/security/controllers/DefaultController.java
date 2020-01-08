package com.experimental.security.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("default")
public class DefaultController {

    @GetMapping("test")
    public String testEndpoint() {
        return "Default Test Endpoint. Accessible unauthorized.";
    }

    @GetMapping("name")
    @CrossOrigin
    public String respondWithName(@RequestParam("name") String name) {
        return "Hi, "+name+"! This endpoint is accessible unauthorized.";
    }

    @GetMapping("auth")
    public String authorizedController() {
        return "You have just accessed an authorized endpoint.";
    }
    
}