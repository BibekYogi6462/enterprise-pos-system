package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.configuration.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;

@Data
public class UserDto {

        @Id
        private Long id;


        private String fullName;

        private String email;

        private String phone;


        private UserRole role;

        private String password;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime lastLogin;


    }
