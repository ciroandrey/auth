package com.forastero.auth.controller;

import com.forastero.auth.dto.CreateUserDTO;
import com.forastero.auth.dto.LoginDTO;
import com.forastero.auth.model.User;
import com.forastero.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody LoginDTO loginDTO) {
        return accountService.authenticate(loginDTO);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserDTO user) {
        return accountService.saveUser(user);
    }
}
