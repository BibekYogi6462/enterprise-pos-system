package com.bibek.enterprisepossystem.repository;

import com.bibek.enterprisepossystem.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Store findByStoreAdminId(Long id);


}