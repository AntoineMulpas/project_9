package com.medilabo.microservice_patient.exceptions;

public class PatientDoesNotExistException extends Exception{

    public PatientDoesNotExistException(String errorMessage) {
        super(errorMessage);
    }

}
