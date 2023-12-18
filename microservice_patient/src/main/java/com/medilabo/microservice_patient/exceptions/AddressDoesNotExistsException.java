package com.medilabo.microservice_patient.exceptions;

public class AddressDoesNotExistsException extends Exception{

    public AddressDoesNotExistsException(String errorMessage) {
        super(errorMessage);
    }

}
