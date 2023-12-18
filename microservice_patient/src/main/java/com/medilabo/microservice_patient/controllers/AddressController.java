package com.medilabo.microservice_patient.controllers;

import com.medilabo.microservice_patient.exceptions.AddressDoesNotExistsException;
import com.medilabo.microservice_patient.models.Address;
import com.medilabo.microservice_patient.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/add")
    public ResponseEntity<Address> addNewAddress(
            @RequestBody Address address
    ) {
        try {
            Address added = addressService.addNewAddress(address);
            return ResponseEntity.ok().body(added);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findAddressById(
            @PathVariable Long id
    ) {
        try {
            Address address = addressService.findAddressById(id);
            return ResponseEntity.ok().body(address);
        } catch (AddressDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteAddressById(
            @PathVariable Long id
    ) {
        try {
            addressService.deleteAddressById(id);
            return ResponseEntity.ok().body("Address deleted with success.");
        } catch (AddressDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address does not exist for id: " + id);
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Address> updateAddressById(
            @PathVariable Long id,
            @RequestBody Address address) {
        try {
            Address updateAddressById  = addressService.updateAddressById(id, address);
            return ResponseEntity.ok().body(updateAddressById);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
