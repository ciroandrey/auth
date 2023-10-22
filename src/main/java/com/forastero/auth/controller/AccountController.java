package com.forastero.auth.controller;

import com.forastero.auth.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public String account() {
        return "Working";
    }
}
