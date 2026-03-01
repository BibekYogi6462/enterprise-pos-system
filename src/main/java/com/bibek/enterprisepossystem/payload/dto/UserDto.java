package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.domain.UserRole;
import jakarta.persistence.Id;
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
