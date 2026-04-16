package com.java.ResfulAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@Controller
public class homecontroller {
    @GetMapping("/")
    public String getMethodName() {
        return " deptrai";
    }

}
