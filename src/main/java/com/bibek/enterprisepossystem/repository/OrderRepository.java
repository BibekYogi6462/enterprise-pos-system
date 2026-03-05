package com.bibek.enterprisepossystem.repository;

import com.bibek.enterprisepossystem.model.Order;
import com.bibek.enterprisepossystem.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);
    List<Order> findByBranchId(Long branchId);
    List<Order> findByCashierId(Long cashierId);
    List<Order> findByBranchIdAndCreatedAtBetween(Long branchId,
                                                  java.time.LocalDateTime from,
                                                  java.time.LocalDateTime to);
    List<Order> findByCashierIdAndCreatedAtBetween(User cashier,
                                                   java.time.LocalDateTime from,
                                                   java.time.LocalDateTime to);

    List<Order> findTop5ByBranchIdOrderByCreatedAtDesc(Long branchId);
}
