package com.medilabo.microservice_patient.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medilabo.microservice_patient.models.Address;
import com.medilabo.microservice_patient.models.Gender;
import com.medilabo.microservice_patient.models.Patient;
import com.medilabo.microservice_patient.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private PatientController underTest;

    private Patient patient;

    private Address address;


    @BeforeEach
    void setUp() {
        underTest = new PatientController(patientService);
        address = new Address(
                1L,
                "1 rue",
                "59000",
                "Lille"
        );
        patient = new Patient(
                1L,
                "Antoine",
                "M",
                LocalDate.now(),
                "0483478394",
                Gender.MALE,
                address

        );
    }

    @Test
    @WithMockUser
    void addNewPatient() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/patient/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient))
        ).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testFindPatientById_Exists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void deletePatientById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/patient/delete/1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updatePatientById() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/patient/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient))
        ).andExpect(status().isOk());
    }
}