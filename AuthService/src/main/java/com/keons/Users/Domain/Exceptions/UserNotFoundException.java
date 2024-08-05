package com.keons.Users.Domain.Exceptions;

import com.keons.Core.Domain.Exceptions.ElementNotFoundException;

public class UserNotFoundException extends ElementNotFoundException {

    public UserNotFoundException() {
        super("No se ha encontrado usuario");
    }
}
