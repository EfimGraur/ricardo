package net.proselyte.springsecuritydemo.controller;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private Long userID;
    private String password;
}
