package com.medilabo.microservice_patient.repositories;

import com.medilabo.microservice_patient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCaseAndDobEqualsAndAddress_City(
            String firstname, String lastname, LocalDate dob, String city);

}
