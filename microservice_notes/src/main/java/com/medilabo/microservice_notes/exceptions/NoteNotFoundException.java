package com.medilabo.microservice_notes.exceptions;

public class NoteNotFoundException extends Exception {

    public NoteNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
