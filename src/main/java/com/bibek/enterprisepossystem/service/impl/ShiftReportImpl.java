package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.domain.PaymentType;
import com.bibek.enterprisepossystem.mapper.ShiftReportMapper;
import com.bibek.enterprisepossystem.model.*;
import com.bibek.enterprisepossystem.payload.dto.ShiftReportDto;
import com.bibek.enterprisepossystem.repository.*;
import com.bibek.enterprisepossystem.service.BranchService;
import com.bibek.enterprisepossystem.service.ShiftReportService;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShiftReportImpl implements ShiftReportService {

    private  final ShiftReportRepository shiftReportRepository;
    private final UserService userService;
    private  final BranchService branchService;
    private final BranchRepository branchRepository;
    private final RefundRepository refundRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;




    @Override
    public ShiftReportDto startShift() throws Exception {
        User currentUser = userService.getCurrentUser();
        LocalDateTime shiftStart = LocalDateTime.now();
        LocalDateTime startOfDay = shiftStart.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endofDay = shiftStart.withHour(23).withMinute(59).withSecond(59);
        Optional<ShiftReport> existing = shiftReportRepository.findByCashierAndShiftStartBetween(
                currentUser, startOfDay, endofDay
        );
        if(existing.isPresent()){
            throw new Exception("Shift already started for today");
        }
        Branch branch = currentUser.getBranch();
        ShiftReport shiftReport = ShiftReport.builder()
                .cashier(currentUser)
                .shiftStart(shiftStart)
                .branch(branch)
                .build();
        ShiftReport savedReport = shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedReport);
    }

    @Override
    public ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception {

        User currentUser = userService.getCurrentUser();
        ShiftReport shiftReport = shiftReportRepository.findTopByCashierIdAndShiftEndIsNullOrderByShiftStartDesc(
                currentUser
        ).orElseThrow(
                () -> new Exception("No active shift found for the cashier")
        );

      shiftReport.setShiftEnd(shiftEnd);
      List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
              currentUser.getId(),
              shiftReport.getShiftStart(), shiftReport.getShiftEnd());
      double totalRefunds = refunds.stream()
              .mapToDouble(refund -> refund.getAmount() != null
              ? refund.getAmount() : 0.0
              ).sum();

      List<Order> orders = orderRepository.findByCashierIdAndCreatedAtBetween(
              currentUser,
              shiftReport.getShiftStart(), shiftReport.getShiftEnd()
      );

      double totalSales =orders.stream()
              .mapToDouble(Order::getTotalAmount)
              .sum();
      int totalOrders = orders.size();
      double netSales = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setNetSales(netSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport = shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedReport);
    }


    @Override
    public ShiftReportDto getShiftReportById(Long id) throws Exception {
        return shiftReportRepository.findById(id)
                .map(ShiftReportMapper::toDTO)
                .orElseThrow(
                        () -> new Exception("Shift report not found with id: " + id)
                );

    }

    @Override
    public List<ShiftReportDto> getAllShiftReports() throws Exception {
        List<ShiftReport> reports = shiftReportRepository.findAll();
        return reports.stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByBranchId(Long branchId) throws Exception {
        List<ShiftReport> reports = shiftReportRepository.findByBranchId(branchId);
        return reports.stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId) throws Exception {
        List<ShiftReport> reports = shiftReportRepository.findByCashierId(cashierId);
        return reports.stream()
                .map(ShiftReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ShiftReportDto getCurrentShiftProgress(Long cashierId) throws Exception {
        User user = userService.getCurrentUser();
        ShiftReport shiftReport = shiftReportRepository.findTopByCashierIdAndShiftEndIsNullOrderByShiftStartDesc(
                user)
                .orElseThrow(
                        () -> new Exception("No active shift found for the date")
                );
        LocalDateTime now = LocalDateTime.now();
        List<Order> orders = orderRepository.findByCashierIdAndCreatedAtBetween(
                user,
                shiftReport.getShiftStart(),
                now
        );
            List<Refund> refunds = refundRepository.findByCashierIdAndCreatedAtBetween(
                    user.getId(),
                    shiftReport.getShiftStart(), now);
            double totalRefunds = refunds.stream()
                    .mapToDouble(refund -> refund.getAmount() != null
                            ? refund.getAmount() : 0.0
                    ).sum();

        double totalSales =orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
        int totalOrders = orders.size();
        double netSales = totalSales - totalRefunds;

        shiftReport.setTotalRefunds(totalRefunds);
        shiftReport.setTotalSales(totalSales);
        shiftReport.setNetSales(netSales);
        shiftReport.setTotalOrders(totalOrders);
        shiftReport.setRecentOrders(getRecentOrders(orders));
        shiftReport.setTopSellingProducts(getTopSellingProducts(orders));
        shiftReport.setPaymentSummaries(getPaymentSummaries(orders, totalSales));
        shiftReport.setRefunds(refunds);

        ShiftReport savedReport = shiftReportRepository.save(shiftReport);
        return ShiftReportMapper.toDTO(savedReport);


    }

    //Helper method to get shift report by cashier and date

    @Override
    public ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception {
        User cashier = userRepository.findById(cashierId).orElseThrow(
                () -> new Exception("Cashier not found with id: " + cashierId)
        );
        LocalDateTime start = date.withHour(0).withMinute(0).withSecond(0);
        LocalDateTime end = date.withHour(23).withMinute(59).withSecond(59);
        ShiftReport report = shiftReportRepository.findByCashierAndShiftStartBetween(
                cashier, start, end
        ).orElseThrow(
                () -> new Exception("No shift report found for cashier on the specified date")
        );


        return ShiftReportMapper.toDTO(report);
    }

    private List<PaymentSummary> getPaymentSummaries(List<Order> orders, double totalSales) {
        Map<PaymentType, List<Order>> grouped = orders.stream()
                .collect(Collectors.groupingBy(order->order.getPaymentType()!=null?
                        order.getPaymentType() : PaymentType.CASH));

        List<PaymentSummary> summaries = new ArrayList<>();
        for(Map.Entry<PaymentType, List<Order>> entry : grouped.entrySet()){
            double amount = entry.getValue().stream()
                    .mapToDouble(Order::getTotalAmount)
                    .sum();
            int transaction = entry.getValue().size();
            double percentage = (amount / totalSales) * 100;
            PaymentSummary ps = new PaymentSummary();
            ps.setPaymentType(entry.getKey());
            ps.setTotalAmount(amount);
            ps.setTransactionCount(transaction);
            ps.setPercentage(percentage);
            summaries.add(ps);
        }
        return  summaries;

    }

    private List<Product> getTopSellingProducts(List<Order> orders) {

        Map<Product, Integer> productSalesMap = new HashMap<>();

        //
        for(Order order : orders){
            for(OrderItem item : order.getItems()){
                Product product = item.getProduct();
                int quantity = item.getQuantity();
                productSalesMap.put(product, productSalesMap.getOrDefault(product, 0) + quantity);
            }
        }

        return productSalesMap.entrySet().stream()
                .sorted((a,b)->b.getValue().compareTo(a.getValue()))
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());


    }

    private List<Order> getRecentOrders(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getCreatedAt).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }



}
