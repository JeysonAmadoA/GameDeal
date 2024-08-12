package com.keons.Auth.Infrastructure.Controllers;

import com.keons.Auth.Application.Services.AuthService;
import com.keons.Auth.Domain.Constants.Security.Role;
import com.keons.Auth.Domain.Dto.LoginDto;
import com.keons.Auth.Domain.Dto.RegisterDto;
import com.keons.Core.Infrastructure.Controllers.BaseController;
import com.keons.Users.Domain.Dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterDto registerUserDto) {
        registerUserDto.setRole(Role.ADMIN);
        UserDto userCreated = authService.registerUser(registerUserDto);
        Map<String, Object> response = getJsonResponse(userCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/register-customer")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody RegisterDto registerUserDto) {
        registerUserDto.setRole(Role.CUSTOMER);
        UserDto userCreated = authService.registerUser(registerUserDto);
        Map<String, Object> response = getJsonResponse(userCreated, HttpStatus.CREATED.value());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        String jwt = authService.loginUser(loginDto);
        Map<String, Object> response = getJsonResponse(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
