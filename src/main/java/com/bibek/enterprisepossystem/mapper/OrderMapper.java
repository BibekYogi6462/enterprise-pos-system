package com.bibek.enterprisepossystem.mapper;

import com.bibek.enterprisepossystem.model.Order;
import com.bibek.enterprisepossystem.payload.dto.OrderDto;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto toDTO(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .customer(order.getCustomer())
                .paymentType(order.getPaymentType())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream()
                .map(OrderItemMapper::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }

}
