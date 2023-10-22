package com.forastero.auth.seed;

import com.forastero.auth.dto.CreateUserDTO;
import com.forastero.auth.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserSeed {

    private final AccountService accountService;

    @Autowired
    public UserSeed(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    void seedUser(){
        accountService.saveUser(
                CreateUserDTO.builder()
                        .username("potato")
                        .email("potato@outlook.com")
                        .password("potato")
                        .build()
        );
    }
}
