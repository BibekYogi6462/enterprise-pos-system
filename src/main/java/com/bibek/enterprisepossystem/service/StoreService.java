package com.bibek.enterprisepossystem.service;

import com.bibek.enterprisepossystem.domain.StoreStatus;
import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.model.Store;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.StoreDto;

import java.util.List;


public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws Exception;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;

    StoreDto moderateStore(Long id, StoreStatus status) throws Exception;


}
