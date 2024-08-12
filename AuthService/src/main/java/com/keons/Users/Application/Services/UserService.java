package com.keons.Users.Application.Services;

import com.keons.Users.Domain.Dto.UpdateUserDto;
import com.keons.Users.Domain.Dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto filterUserById(Long userId);

    List<UserDto> findAllUsers();

    List<UserDto> filterUsersByEmail(String email);

    List<UserDto> filterUsersByUsername(String username);

    UserDto updateUser(UpdateUserDto userDto, Long userId);

    void deleteUserById(Long userId);

}
