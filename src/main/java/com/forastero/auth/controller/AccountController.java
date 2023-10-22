package com.forastero.auth.controller;

import com.forastero.auth.config.jwt.JwtTokenUtil;
import com.forastero.auth.dto.CreateUserDTO;
import com.forastero.auth.dto.LoginDTO;
import com.forastero.auth.model.User;
import com.forastero.auth.service.AccountService;
import com.forastero.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public AccountController(AccountService accountService,
                             JwtTokenUtil jwtTokenUtil,
                             CustomUserDetailsService userDetailsService) {
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public Boolean login(@RequestBody LoginDTO login) {
        return accountService.doLogin(login);
    }

    @PostMapping("/create")
    public User createUser(@RequestBody CreateUserDTO user) {
        return accountService.saveUser(user);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody LoginDTO login) {
        return accountService.authenticate(login);
    }
}
