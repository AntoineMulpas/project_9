package com.medilabo.microservice_patient.services;

import com.medilabo.microservice_patient.exceptions.AddressDoesNotExistsException;
import com.medilabo.microservice_patient.models.Address;
import com.medilabo.microservice_patient.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    private AddressService underTest;

    @Mock
    private AddressRepository addressRepository;

    private Address address;


    @BeforeEach
    void setUp() {
        underTest = new AddressService(addressRepository);
        address = new Address(1L, "1 Rue", "1234", "Paris");
    }

    @Test
    void addNewAddress() {
        when(addressRepository.save(any())).thenReturn(address);
        assertEquals(address.getAddress(), underTest.addNewAddress(address).getAddress());
        assertEquals(address.getZip(), underTest.addNewAddress(address).getZip());
        assertEquals(address.getCity(), underTest.addNewAddress(address).getCity());
    }

    @Test
    void findAddressById() throws AddressDoesNotExistsException {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        assertEquals(address, underTest.findAddressById(1L));
    }

    @Test
    void findAddressByIdShouldThrowIfPatientDoesNotExist()  {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(AddressDoesNotExistsException.class, () -> underTest.findAddressById(1L));
    }

    @Test
    void updateAddressById() throws AddressDoesNotExistsException {
        Address toUpdate = address;
        String newCity = "New York";
        toUpdate.setCity(newCity);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        when(addressRepository.save(toUpdate)).thenReturn(toUpdate);
        assertEquals(newCity, underTest.updateAddressById(1L, toUpdate).getCity());
    }

    @Test
    void updateAddressByIdShouldThrowIfAddressDoesNotExist() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(AddressDoesNotExistsException.class, () -> underTest.updateAddressById(1L, address));
    }

    @Test
    void deleteAddressById() {
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        assertDoesNotThrow(() -> underTest.deleteAddressById(1L));
    }

    @Test
    void findAllAddresses() {
        when(addressRepository.findAll()).thenReturn(List.of(address));
        assertEquals(1, underTest.findAllAddresses().size());
    }
}