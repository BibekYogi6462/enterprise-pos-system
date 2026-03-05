package com.bibek.enterprisepossystem.repository;

import com.bibek.enterprisepossystem.model.Refund;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.RefundDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Ref;
import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {


    List<Refund> findByCashierIdAndCreatedAtBetween(Long cashier,
                                                  LocalDateTime from,
                                                  LocalDateTime to
                                                  );
    List<Refund> findByCashierId(Long id);

    List<Refund> findByShiftReportId(Long id);

    List<Refund> findByBranchId(Long id);


    List<RefundDto> getRefundByCashierAndCreatedAtBetween();
}
