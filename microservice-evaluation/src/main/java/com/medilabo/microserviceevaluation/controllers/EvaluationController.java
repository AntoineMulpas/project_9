package com.medilabo.microserviceevaluation.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medilabo.microserviceevaluation.models.Evaluation;
import com.medilabo.microserviceevaluation.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/evaluation")
@CrossOrigin(origins = "*")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }


    @GetMapping("/{patientId}/{gender}/{dob}")
    public ResponseEntity<Evaluation> getRiskForSpecificPatient(
            @PathVariable String patientId,
            @PathVariable String gender,
            @PathVariable LocalDate dob,
            @RequestHeader HttpHeaders headers
            ) throws JsonProcessingException {
        String authorization = Objects.requireNonNull(headers.get("Authorization")).get(0);
        String token = authorization.substring(7);
        Evaluation evaluation = evaluationService.calculateRisk(patientId, dob, gender, token);
        return ResponseEntity.ok().body(evaluation);
    }

}
