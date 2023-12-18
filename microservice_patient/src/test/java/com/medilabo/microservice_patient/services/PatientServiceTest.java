package com.medilabo.microservice_patient.services;

import com.medilabo.microservice_patient.exceptions.PatientAlreadyExistsException;
import com.medilabo.microservice_patient.exceptions.PatientDoesNotExistException;
import com.medilabo.microservice_patient.models.Address;
import com.medilabo.microservice_patient.models.Gender;
import com.medilabo.microservice_patient.models.Patient;
import com.medilabo.microservice_patient.repositories.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    private PatientService underTest;

    @Mock
    private PatientRepository patientRepository;

    private Patient patient;

    @BeforeEach
    void setUp() {
        underTest = new PatientService(patientRepository);
        patient = new Patient(
                1L,
                "Mathieu",
                "Nebra",
                LocalDate.of(1980, 3, 20),
                "0302020202",
                Gender.MALE,
                new Address(
                        1L,
                        "1 rue",
                        "12345",
                        "Paris"
                ));
    }

    @Test
    void addNewPatient() throws PatientAlreadyExistsException {
        when(patientRepository.save(any())).thenReturn(patient);
        assertEquals(patient.getFirstName(), underTest.addNewPatient(patient).getFirstName());
        assertEquals(patient.getLastName(), underTest.addNewPatient(patient).getLastName());
        assertEquals(patient.getDob(), underTest.addNewPatient(patient).getDob());
        assertEquals(patient.getPhone(), underTest.addNewPatient(patient).getPhone());
        assertEquals(patient.getAddress(), underTest.addNewPatient(patient).getAddress());
    }

    @Test
    void findPatientById() throws PatientDoesNotExistException {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        assertEquals(patient.getFirstName(), underTest.findPatientById(1L).getFirstName());
        assertEquals(patient.getLastName(), underTest.findPatientById(1L).getLastName());
        assertEquals(patient.getDob(), underTest.findPatientById(1L).getDob());
        assertEquals(patient.getPhone(), underTest.findPatientById(1L).getPhone());
        assertEquals(patient.getAddress(), underTest.findPatientById(1L).getAddress());
    }

    @Test
    void findPatientByIdShouldThrowIfPatientDoesNotExist() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PatientDoesNotExistException.class, () -> underTest.findPatientById(1L));

    }

    @Test
    void updatePatientById() throws PatientDoesNotExistException {
        Patient toUpdate = patient;
        String other_last_name = "Other Last Name";
        toUpdate.setLastName(other_last_name);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(underTest.updatePatientById(1L, toUpdate)).thenReturn(toUpdate);
        underTest.updatePatientById(1L, toUpdate);
        assertEquals(patient.getFirstName(), underTest.updatePatientById(1L, toUpdate).getFirstName());
        assertEquals(other_last_name, underTest.updatePatientById(1L, toUpdate).getLastName());
        assertEquals(patient.getDob(), underTest.updatePatientById(1L, toUpdate).getDob());
        assertEquals(patient.getPhone(), underTest.updatePatientById(1L, toUpdate).getPhone());
        assertEquals(patient.getAddress(), underTest.updatePatientById(1L, toUpdate).getAddress());
    }

    @Test
    void updatePatientByIdShouldThrowIfPatientDoesNotExist() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PatientDoesNotExistException.class, () -> underTest.updatePatientById(1L, patient));
    }

    @Test
    void deletePatientById() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        assertDoesNotThrow(() -> underTest.deletePatientById(1L));
    }

    @Test
    void deletePatientByIdShouldThrowIfPatientDoesNotExist() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PatientDoesNotExistException.class, () -> underTest.deletePatientById(1L));
    }

    @Test
    void findAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(patient));
        assertEquals(1, underTest.findAllPatients().size());
    }
}