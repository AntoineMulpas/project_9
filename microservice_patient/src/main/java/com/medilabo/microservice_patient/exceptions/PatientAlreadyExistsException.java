package com.medilabo.microservice_patient.exceptions;

public class PatientAlreadyExistsException extends Exception{

    public PatientAlreadyExistsException(String message) {
        super(message);
    }
}
