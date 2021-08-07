package com.ricardo.pmtool.data;

import lombok.Data;

@Data
public class AuthenticationData {
    private String email;
    private Long userID;
    private String password;
}
