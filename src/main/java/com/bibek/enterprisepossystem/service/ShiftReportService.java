package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.model.ShiftReport;
import com.bibek.enterprisepossystem.payload.dto.ShiftReportDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService {

    ShiftReportDto startShift() throws Exception;
    ShiftReportDto endShift(Long shiftReportId, LocalDateTime shiftEnd) throws Exception;

    ShiftReportDto getShiftReportById(Long id) throws Exception;

    List<ShiftReportDto> getAllShiftReports() throws Exception;

    List<ShiftReportDto> getShiftReportsByCashierId(Long cashierId) throws Exception;

    List<ShiftReportDto> getShiftReportsByBranchId(Long branchId) throws Exception;


    ShiftReportDto getCurrentShiftProgress(Long cashierId) throws Exception;

    ShiftReportDto getShiftByCashierAndDate(Long cashierId, LocalDateTime date) throws Exception;




}
