package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.payload.dto.UserDto;
import com.bibek.enterprisepossystem.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;
}
