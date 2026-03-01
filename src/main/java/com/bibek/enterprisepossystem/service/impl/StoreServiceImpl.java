package com.bibek.enterprisepossystem.service.impl;

import com.bibek.enterprisepossystem.domain.StoreStatus;
import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.mapper.StoreMapper;
import com.bibek.enterprisepossystem.model.Store;
import com.bibek.enterprisepossystem.model.StoreContact;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.StoreDto;
import com.bibek.enterprisepossystem.repository.StoreRepository;
import com.bibek.enterprisepossystem.service.StoreService;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public StoreDto createStore(StoreDto storeDto, User user) {
        Store store = StoreMapper.toEntity(storeDto, user);
        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public StoreDto getStoreById(Long id) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()->new Exception("store not found")
        );
        return StoreMapper.toDTO(store);
    }

    @Override
    public List<StoreDto> getAllStores() {
        List<Store> dtos = storeRepository.findAll();
        return dtos.stream().map(StoreMapper::toDTO).collect(Collectors.toList());

    }

    @Override
    public Store getStoreByAdmin() throws UserException {
        User admin = userService.getCurrentUser();
        return storeRepository.findByStoreAdminId(admin.getId());
    }

    @Override
    public StoreDto updateStore(Long id, StoreDto storeDto) throws Exception {
        User currentUser = userService.getCurrentUser();
        Store existing = storeRepository.findByStoreAdminId(currentUser.getId());

        if(existing == null){
            throw new Exception("store not found");
        }
        existing.setBrand(storeDto.getBrand());
        existing.setDescription(storeDto.getDescription());

        if(existing.getStoreType()!=null){
            existing.setStoreType(storeDto.getStoreType());
        }

        if(storeDto.getContact()!=null){
            StoreContact contact = StoreContact.builder()
                    .address(storeDto.getContact().getAddress())
                    .phone(storeDto.getContact().getPhone())
                    .email(storeDto.getContact().getEmail())
                    .build();
            existing.setContact(contact);
        }
        Store updatedStore = storeRepository.save(existing);
        return StoreMapper.toDTO(updatedStore);
    }

    @Override
    public void deleteStore(Long id) throws UserException {
        Store store = getStoreByAdmin();
        storeRepository.delete(store);

    }

    @Override
    public StoreDto getStoreByEmployee() throws UserException {
        User currentUser = userService.getCurrentUser();

        if(currentUser == null){
            throw new UserException("You dont have permission to access store");
        }
        return  StoreMapper.toDTO(currentUser.getStore());

    }

    @Override
    public StoreDto moderateStore(Long id, StoreStatus status) throws Exception {
        Store store = storeRepository.findById(id).orElseThrow(
                ()->new Exception("store not found")
        );
        store.setStatus(status);
        Store updatedStore =  storeRepository.save(store);
        return StoreMapper.toDTO(updatedStore);
    }
}
