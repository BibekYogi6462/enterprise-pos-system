package com.bibek.enterprisepossystem.repository;

import com.bibek.enterprisepossystem.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, Long> {

    List<Branch> findByStore_Id(Long storeId);

}
