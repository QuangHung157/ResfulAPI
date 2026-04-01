package com.java.ResfulAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Controller
public class homecontroller {
    @GetMapping("/")
    public String getMethodName() {
        return " deptrai";
    }

}
