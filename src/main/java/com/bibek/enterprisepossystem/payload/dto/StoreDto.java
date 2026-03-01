package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.domain.StoreStatus;
import com.bibek.enterprisepossystem.model.StoreContact;
import com.bibek.enterprisepossystem.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDto {

    private Long id;

    private String brand;


    private UserDto StoreAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact contact;

}
