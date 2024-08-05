package com.keons.Auth.Infrastructure.Services;

import com.keons.Auth.Application.Services.AuthService;
import com.keons.Auth.Application.Services.JwtService;
import com.keons.Auth.Domain.Dto.LoginDto;
import com.keons.Auth.Domain.Dto.RegisterDto;
import com.keons.Users.Application.Repositories.UserRepository;
import com.keons.Users.Domain.Dto.UserDto;
import com.keons.Users.Domain.Entities.User;
import com.keons.Users.Domain.Exceptions.RegisterUserException;
import com.keons.Users.Domain.Exceptions.UserNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.keons.Auth.Domain.Helpers.AuthHelper.verifyRegisterPasswords;
import static com.keons.Users.Application.Mappers.UserMapper.RegisterUserDtoMapper.toEntity;
import static com.keons.Users.Application.Mappers.UserMapper.UserDtoMapper.toDto;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDto registerUser(RegisterDto registerDto) {
        User userRegistered;
        verifyRegisterPasswords(registerDto);
        try {
            User newUser = toEntity(registerDto);
            userRegistered = userRepository.save(newUser);
        } catch (Exception e) {
            throw new RegisterUserException(e.getMessage());
        }
        return toDto(userRegistered);
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword());

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(UserNotFoundException::new);
        authenticationManager.authenticate(authToken);
        return jwtService.generateToken(user);
    }
}
