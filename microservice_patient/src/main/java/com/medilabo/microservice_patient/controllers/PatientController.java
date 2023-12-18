package com.medilabo.microservice_patient.controllers;

import com.medilabo.microservice_patient.exceptions.PatientAlreadyExistsException;
import com.medilabo.microservice_patient.exceptions.PatientDoesNotExistException;
import com.medilabo.microservice_patient.models.Patient;
import com.medilabo.microservice_patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/add", consumes = "application/JSON")
    public ResponseEntity<Patient> addNewPatient(
            @RequestBody Patient patient) {
        try {
            Patient added = patientService.addNewPatient(patient);
            return ResponseEntity.ok().body(added);
        } catch (PatientAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> findAllPatient() {
        System.out.println("Called findAllPatient");
        List <Patient> allPatients = patientService.findAllPatients();
        allPatients.forEach(System.out::println);
        return ResponseEntity.ok().body(allPatients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Long id) {
        try {
            Patient patient = patientService.findPatientById(id);
            return ResponseEntity.ok().body(patient);
        } catch (PatientDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(
            @PathVariable Long id
    ) {
        try {
            patientService.deletePatientById(id);
            return ResponseEntity.ok().body("Patient deleted with success.");
        } catch (PatientDoesNotExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping(value = "/update/{id}" , consumes = "application/JSON")
    public ResponseEntity<Patient> updatePatientById(
            @PathVariable Long id,
            @RequestBody Patient patient) {
        try {
            Patient updateById = patientService.updatePatientById(id, patient);
            return ResponseEntity.ok().body(updateById);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
