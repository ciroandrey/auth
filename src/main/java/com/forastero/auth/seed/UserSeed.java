package com.forastero.auth.seed;

import com.forastero.auth.model.User;
import com.forastero.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserSeed {

    private final UserService userService;

    @Autowired
    public UserSeed(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    void seedUser(){
        userService.createUser(
                User.builder()
                        .username("batata")
                        .email("batata@outlook.com")
                        .build()
        );
    }
}
