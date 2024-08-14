package com.keons.Core.Domain.Exceptions;

public class BusinessLogicException extends RuntimeException
{
    public BusinessLogicException(String message){
        super(message);
    }
}
