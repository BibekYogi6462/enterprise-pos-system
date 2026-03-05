package com.bibek.enterprisepossystem.mapper;

import com.bibek.enterprisepossystem.model.Refund;
import com.bibek.enterprisepossystem.payload.dto.RefundDto;

public class RefundMapper {
    public static RefundDto toDTO(Refund refund) {

        return RefundDto.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId())
                .shiftReportId(refund.getShiftReport()!=null?
                        refund.getShiftReport().getId():null)
                .createdAt(refund.getCreatedAt())
                .build();

    }

}
