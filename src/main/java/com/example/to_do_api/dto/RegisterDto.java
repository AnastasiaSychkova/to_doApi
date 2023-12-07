package com.example.to_do_api.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String mail;
    private String password;
    private String role;
}
