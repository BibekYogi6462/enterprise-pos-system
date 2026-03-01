package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.configuration.JwtProvider;
import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.repository.UserRepository;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("Invalid Token");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("User not found");
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("User not found");
        }
        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException, Exception {
        return userRepository.findById(id).orElseThrow(
                ()->new Exception ("User not Found")
        );
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
