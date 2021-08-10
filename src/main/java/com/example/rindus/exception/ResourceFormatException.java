package com.example.rindus.exception;

import java.util.ArrayList;
import java.util.List;

public class ResourceFormatException extends Exception{

    private List<String> errorMessages = new ArrayList<>();

    public ResourceFormatException(String msg) {
        super(msg);
    }

    public void addErrorMessage(String message) {
        this.errorMessages.add(message);
    }
}
