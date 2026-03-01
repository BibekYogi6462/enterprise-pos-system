package com.bibek.enterprisepossystem.controller;


import com.bibek.enterprisepossystem.exceptions.UserException;
import com.bibek.enterprisepossystem.mapper.UserMapper;
import com.bibek.enterprisepossystem.model.User;
import com.bibek.enterprisepossystem.payload.dto.UserDto;
import com.bibek.enterprisepossystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<UserDto> getUserProfile(
          @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException, Exception {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }
}
