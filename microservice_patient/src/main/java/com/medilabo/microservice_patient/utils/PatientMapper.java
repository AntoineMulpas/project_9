package com.medilabo.microservice_patient.utils;

import com.medilabo.microservice_patient.models.Patient;

public class PatientMapper {

    public static Patient map(Patient initial, Patient update) {
        if (update.getFirstName() != null) {
            initial.setFirstName(update.getFirstName());
        }

        if (update.getLastName() != null) {
            initial.setLastName(update.getLastName());
        }

        if (update.getAddress() != null) {
            initial.setAddress(update.getAddress());
        }

        if (update.getDob() != null) {
            initial.setDob(update.getDob());
        }

        if (update.getGender() != null) {
            initial.setGender(update.getGender());
        }

        if (update.getPhone() != null) {
            initial.setPhone(update.getPhone());
        }
        return initial;
    }

}
