package com.bibek.enterprisepossystem.repository;

import com.bibek.enterprisepossystem.model.ShiftReport;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.ShiftReportDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport, Long> {

    List<ShiftReport> findByCashierId(Long id);
    List<ShiftReport> findByBranchId(Long id);

    Optional<ShiftReport> findTopByCashierIdAndShiftEndIsNullOrderByShiftStartDesc(User cashier);

    Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier, java.time.LocalDateTime start, java.time.LocalDateTime end);


}
