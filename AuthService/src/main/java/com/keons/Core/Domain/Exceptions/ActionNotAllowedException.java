package com.keons.Core.Domain.Exceptions;

public class ActionNotAllowedException extends RuntimeException {

    public ActionNotAllowedException() {
        super("No tiene acceso para realizar esta acción");
    }
}
