package com.medilabo.microserviceevaluation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.microserviceevaluation.models.Note;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NoteService {

    @Value("${NOTE_IP_ADDRESS}")
    String NOTE_IP_ADDRESS;

    public List <Note> getAllNoteByPatientId(String patientId, String token) throws JsonProcessingException {
        String url = "http://" + NOTE_IP_ADDRESS + ":8765/api/v1/note/patient/" + patientId;
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        org.springframework.http.HttpEntity<String> httpEntity = new org.springframework.http.HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, org.springframework.http.HttpMethod.GET, httpEntity, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody(), new TypeReference <>() {
        });
    }

}
