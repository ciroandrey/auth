package com.forastero.auth.scheduler;

import com.forastero.auth.dto.CreateUserDTO;
import com.forastero.auth.model.Role;
import com.forastero.auth.service.AccountService;
import com.forastero.auth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitialSeed {

    private final RoleService roleService;
    private final AccountService accountService;

    @Autowired
    public InitialSeed(RoleService roleService,
                       AccountService accountService) {
        this.roleService = roleService;
        this.accountService = accountService;
    }

    @PostConstruct
    public void init() {
        // Criando roles
        roleService.createRoleIfNotExists("ADMIN");
        roleService.createRoleIfNotExists("USER");
        Set<Role> adminRole = roleService.findAllByName("ADMIN");
        Set<Role> userRole = roleService.findAllByName("USER");
        adminRole.addAll(userRole);

        // Criando users
        accountService.saveUser(
                CreateUserDTO.builder()
                        .username("potato")
                        .email("potato@outlook.com")
                        .password("123")
                        .roles(new HashSet<>(userRole))
                        .build()
        );
        accountService.saveUser(
                CreateUserDTO.builder()
                        .username("admin")
                        .email("admin@outlook.com")
                        .password("123")
                        .roles(new HashSet<>(adminRole))
                        .build()
        );
    }
}
