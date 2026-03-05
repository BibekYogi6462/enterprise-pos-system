package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.domain.PaymentType;
import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Customer;
import com.bibek.enterprisepossystem.model.OrderItem;
import com.bibek.enterprisepossystem.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;

    private Long customerId;

    private BranchDto branch;

    private UserDto cashier;

    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDto> items;



}
