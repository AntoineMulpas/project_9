package com.medilabo.microservice_patient.utils;

import com.medilabo.microservice_patient.models.Address;

public class AddressMapper {

    public static Address map(Address initial, Address update) {
        if (update.getAddress() != null) {
            initial.setAddress(update.getAddress());
        }

        if (update.getCity() != null) {
            initial.setCity(update.getCity());
        }

        if (update.getZip() != null) {
            initial.setZip(update.getZip());
        }

        return initial;
    }

}
