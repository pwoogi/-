package com.dashboard.projectboard.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${override.value}")
    private String value;

    @GetMapping("/test")
    public String test(){
        return "test 성공 " + value;
    }
}
