package com.pvphall.hallclientapi.exceptions;

public class HallPlayerAlreadyRegisteredException extends RuntimeException {

    @Override
    public String getMessage() {

        return "This HallPlayer is already registered.";
    }
}
