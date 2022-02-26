package com.example.webapp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/adminzxc")
    public String adminLogin() {
        return "adminzxc";
    }
}
