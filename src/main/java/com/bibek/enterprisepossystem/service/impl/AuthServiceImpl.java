package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.configuration.JwtProvider;
import com.bibek.enterprisepossystem.domain.UserRole;
import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.mapper.UserMapper;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.UserDto;
import com.bibek.enterprisepossystem.payload.response.AuthResponse;
import com.bibek.enterprisepossystem.repository.UserRepository;
import com.bibek.enterprisepossystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        // Check if email already exists
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if(existingUser != null) {
            throw new UserException("Email Id already Registered");
        }

        // Handle role - set default if null
        UserRole role = userDto.getRole();
        if(role == null) {
            role = UserRole.ROLE_BRANCH_CASHIER; // FIXED: Using ROLE_CASHIER as default since ROLE_USER doesn't exist
        }

        // Prevent admin signup
        if(role.equals(UserRole.ROLE_ADMIN)) {
            throw new UserException("Role Admin is not allowed");
        }

        // Create new user
        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(role);
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        // Load UserDetails and create Authentication
        UserDetails userDetails = customUserImplementation.loadUserByUsername(savedUser.getEmail());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDTO(savedUser));
        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UserException("User not found");
        }

        user.setLastLogin(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Logged In Successfully");
        authResponse.setUser(UserMapper.toDTO(user));
        return authResponse;
    }

    private Authentication authenticate(String email, String password) throws UserException {
        UserDetails userDetails = customUserImplementation.loadUserByUsername(email);
        if(userDetails == null) {
            throw new UserException("Email Id doesn't exist");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Password doesn't match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}