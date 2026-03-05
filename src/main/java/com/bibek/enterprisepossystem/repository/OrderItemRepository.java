package com.bibek.enterprisepossystem.repository;

import com.bibek.enterprisepossystem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

}
