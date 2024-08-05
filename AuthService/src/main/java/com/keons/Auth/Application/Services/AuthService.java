package com.keons.Auth.Application.Services;

import com.keons.Auth.Domain.Dto.LoginDto;
import com.keons.Auth.Domain.Dto.RegisterDto;
import com.keons.Users.Domain.Dto.UserDto;

public interface AuthService {

    UserDto registerUser(RegisterDto registerDto);

    String loginUser(LoginDto loginDto);
}
