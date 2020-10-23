package com.pvphall.hallclientapi.exceptions;

public class NoSuchHallPlayerException extends RuntimeException {

    @Override
    public String getMessage() {

        return "No HallPlayer was found.";
    }
}
