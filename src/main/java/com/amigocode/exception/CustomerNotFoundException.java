package com.amigocode.exception;

public class CustomerNotFoundException extends IllegalArgumentException{
    public CustomerNotFoundException(String customerNotFound) {
        super("Customer not found");
    }
}
