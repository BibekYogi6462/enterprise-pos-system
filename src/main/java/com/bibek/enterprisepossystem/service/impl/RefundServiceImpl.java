package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.mapper.RefundMapper;
import com.bibek.enterprisepossystem.model.Branch;
import com.bibek.enterprisepossystem.model.Order;
import com.bibek.enterprisepossystem.model.Refund;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.RefundDto;
import com.bibek.enterprisepossystem.repository.OrderRepository;
import com.bibek.enterprisepossystem.repository.RefundRepository;
import com.bibek.enterprisepossystem.service.RefundService;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final UserService userService;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;


    @Override
    public RefundDto createRefund(RefundDto refund) throws Exception {
        User cashier =  userService.getCurrentUser();

        Order order = orderRepository.findById(refund.getOrderId())
                .orElseThrow(() -> new Exception("Order not found with id: " + refund.getOrderId()));

        Branch branch = order.getBranch();

        Refund createdrefund = Refund.builder()
                .order(order)
                .cashier(cashier)
                .branch(branch)
                .reason(refund.getReason())
                .amount(refund.getAmount())
                .paymentType(refund.getPaymentType())
                .createdAt(refund.getCreatedAt())
                .build();
        Refund savedRefund = refundRepository.save(createdrefund);
        return RefundMapper.toDTO(savedRefund);
    }

    @Override
    public List<RefundDto> getAllRefunds() throws Exception {
        return refundRepository.findAll().stream().map(
                RefundMapper::toDTO
        ).toList();
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) throws Exception {
        return refundRepository.findAll().stream()
                .map(
                        RefundMapper::toDTO
                ).collect(Collectors.toList())
                ;
    }

    @Override
    public List<RefundDto> getRefundByShiftReport(Long shiftReportId) throws Exception {
        return refundRepository.findByShiftReportId(shiftReportId).stream()
                .map(
                        RefundMapper::toDTO
                ).collect(Collectors.toList())
                ;
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDataRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return refundRepository.findByCashierIdAndCreatedAtBetween(
                cashierId,
                startDate,
                endDate
        ).stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<RefundDto> getRefundByBranch(Long branchId) throws Exception {
        return refundRepository.findByBranchId(branchId).stream()
                .map(
                        RefundMapper::toDTO
                ).collect(Collectors.toList());
    }

    @Override
    public RefundDto getRefundById(Long refundId) throws Exception {
        return refundRepository.findById(refundId)
                .map(RefundMapper::toDTO)
                .orElseThrow(
                        () -> new Exception("Refund not found with id: " + refundId)
                )
                ;
    }

    @Override
    public void deleteRefund(Long refundId) throws Exception {
        this.getRefundById(refundId);
        refundRepository.deleteById(refundId);

    }
}
