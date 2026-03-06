package com.bibek.enterprisepossystem.mapper;

import com.bibek.enterprisepossystem.model.Order;
import com.bibek.enterprisepossystem.model.Product;
import com.bibek.enterprisepossystem.model.Refund;
import com.bibek.enterprisepossystem.model.ShiftReport;
import com.bibek.enterprisepossystem.payload.dto.OrderDto;
import com.bibek.enterprisepossystem.payload.dto.ProductDto;
import com.bibek.enterprisepossystem.payload.dto.RefundDto;
import com.bibek.enterprisepossystem.payload.dto.ShiftReportDto;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {

    public static ShiftReportDto toDTO(ShiftReport entity) {
        return ShiftReportDto.builder()
                .id(entity.getId())
                .shiftEnd(entity.getShiftEnd())
                .shiftStart(entity.getShiftStart())
                .totalSales(entity.getTotalSales())
                .totalRefunds(entity.getTotalRefunds())
                .netSales(entity.getNetSales())
                .totalOrders(entity.getTotalOrders())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branch(BranchMapper.toDTO(entity.getBranch()))
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();
    }

    private static List<RefundDto> mapRefunds(List<Refund> refunds) {
        if(refunds == null || refunds.isEmpty()) {
            return null;
        }
        return refunds.stream()
                .map(RefundMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static List<ProductDto> mapProducts(List<Product> topSellingProducts) {
        if(topSellingProducts == null || topSellingProducts.isEmpty()) {
            return null;
        }
        return topSellingProducts.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {
        if(recentOrders == null || recentOrders.isEmpty()) {
            return null;
        }
        return recentOrders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());

    }


}
