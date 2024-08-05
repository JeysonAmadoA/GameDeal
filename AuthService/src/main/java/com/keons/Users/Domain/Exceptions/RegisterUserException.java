package com.keons.Users.Domain.Exceptions;

import com.keons.Core.Domain.Exceptions.BusinessLogicException;

public class RegisterUserException extends BusinessLogicException {

    public RegisterUserException(String message) {
        super("Hubo un error al registrar el usuario: " + message);
    }
}
