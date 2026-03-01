package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.model.User;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser() throws UserException;
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id) throws UserException, Exception;
    List<User> getAllUsers();



}
