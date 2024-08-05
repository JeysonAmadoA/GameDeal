package com.keons.Users.Application.Mappers;

import com.keons.Auth.Domain.Dto.RegisterDto;
import com.keons.Users.Domain.Dto.UpdateUserDto;
import com.keons.Users.Domain.Dto.UserDto;
import com.keons.Users.Domain.Entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserMapper {

    public static class UserDtoMapper {

        public static UserDto toDto(User user) {
            return UserDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .documentNumber(user.getDocumentNumber())
                    .address(user.getAddress())
                    .role(String.valueOf(user.getRole()))
                    .build();
        }

        public static User update(User user, UpdateUserDto userDto){
            user.setEmail(userDto.getEmail());
            user.setAddress(userDto.getAddress());
            return user;
        }
    }

    public static class RegisterUserDtoMapper{

        public static User toEntity(RegisterDto registerDto) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encryptedPassword = encoder.encode(registerDto.getPassword());

            return User.builder()
                    .username(registerDto.getUsername())
                    .documentNumber(registerDto.getDocumentNumber())
                    .address(registerDto.getAddress())
                    .email(registerDto.getEmail())
                    .password(encryptedPassword)
                    .role(registerDto.getRole())
                    .build();
        }

    }
}
