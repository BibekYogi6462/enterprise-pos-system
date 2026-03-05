package com.bibek.enterprisepossystem.payload.dto;

import com.bibek.enterprisepossystem.domain.PaymentType;
import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Order;
import com.bibek.enterprisepossystem.model.ShiftReport;
import com.bibek.enterprisepossystem.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class RefundDto {


    private Long id;

    private OrderDto order;
    private Long orderId;

    private String reason;

    private Double amount;


//    private ShiftReport shiftReport;

    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;

    private BranchDto branch;
    private  Long branchId;

    private PaymentType paymentType;

    private LocalDateTime createdAt;
}
