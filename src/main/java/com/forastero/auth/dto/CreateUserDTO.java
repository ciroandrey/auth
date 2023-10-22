package com.forastero.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserDTO {

    private String username;

    private String email;

    private String password;
}
