package com.bibek.enterprisepossystem.controller;

import com.bibek.enterprisepossystem.domain.StoreStatus;
import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.mapper.StoreMapper;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.StoreDto;
import com.bibek.enterprisepossystem.payload.response.ApiResponse;
import com.bibek.enterprisepossystem.service.StoreService;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization")String jwt
                                                ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto, user));

    }


    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAllStore(
                                                 @RequestHeader("Authorization")String jwt
    ) throws Exception {
        return ResponseEntity.ok(storeService.getAllStores());

    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));

    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        return ResponseEntity.
                ok(storeService.getStoreByEmployee());
    }


    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto)
        throws Exception{
        return ResponseEntity.ok(storeService.updateStore(id, storeDto));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id, @RequestParam StoreStatus status)
            throws Exception{
        return ResponseEntity.ok(storeService.moderateStore(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id,
                                                 @RequestHeader("Authorization")String jwt
    ) throws Exception {
        return ResponseEntity.ok(storeService.getStoreById(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id)
            throws Exception{
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("store deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }





}
