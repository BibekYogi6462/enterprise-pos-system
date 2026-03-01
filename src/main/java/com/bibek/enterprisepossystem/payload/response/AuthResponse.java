package com.bibek.enterprisepossystem.payload.response;

import com.bibek.enterprisepossystem.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;


}
