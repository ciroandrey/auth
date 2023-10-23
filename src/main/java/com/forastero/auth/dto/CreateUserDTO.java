package com.forastero.auth.dto;

import com.forastero.auth.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CreateUserDTO {

    private String username;

    private String email;

    private String password;

    private Set<Role> roles;
}
