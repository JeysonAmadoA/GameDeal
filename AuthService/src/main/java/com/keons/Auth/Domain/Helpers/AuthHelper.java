package com.keons.Auth.Domain.Helpers;

import com.keons.Auth.Domain.Constants.Security.Role;
import com.keons.Auth.Domain.Dto.RegisterDto;
import com.keons.Core.Domain.Exceptions.ActionNotAllowedException;
import com.keons.Users.Domain.Dto.UserDto;
import com.keons.Users.Domain.Exceptions.RegisterUserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    public static boolean verifyRegisterPasswords(RegisterDto registerUserDto) throws RegisterUserException {
        if (registerUserDto.getPassword().equals(registerUserDto.getConfirmPassword())) {
            return true;
        } else {
            throw new RegisterUserException("Las contraseñas no coinciden");
        }
    }

    public static Long getActualUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            UserDto userData = (UserDto) authentication.getCredentials();
            return userData.getId();
        }
        else return null;
    }

    public static void verifyUserAccess(Long userId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            UserDto userData = (UserDto) authentication.getCredentials();
            if (userData.getRole().equals(Role.CUSTOMER.name()) && userData.getId() != userId){
                throw new ActionNotAllowedException();
            }
        }

    }

}