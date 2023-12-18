package com.medilabo.microserviceevaluation.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.medilabo.microserviceevaluation.models.Evaluation;
import com.medilabo.microserviceevaluation.models.Gender;
import com.medilabo.microserviceevaluation.models.Note;
import com.medilabo.microserviceevaluation.models.Risk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EvaluationService {

    private final List<String> stringList = List.of("Hémoglobine A1C", "Microalbumine", "Taille", "Poids", "Fumeur", "Fumeuse", "Anormal",
            "Cholestérol", "Vertiges", "Rechute", "Réaction", "Anticorps");

    private final Map<String, Integer> map = transformListToMap();

    private final NoteService noteService;

    @Autowired
    public EvaluationService(NoteService noteService) {
        this.noteService = noteService;
    }

    public Evaluation calculateRisk(String patientId, LocalDate dob, String gender, String token) throws JsonProcessingException {
        List <Note> notes = noteService.getAllNoteByPatientId(patientId, token);
        int age = calculateAge(dob);
        int riskCount = 0;
        for (Note note : notes) {
            String n = note.getNote();
            System.out.println(n);
            for (String s : map.keySet()) {
                if (n.toUpperCase().contains(s.toUpperCase())) {
                    riskCount++;
                }
            }
        }

        System.out.println("RISK COUNT: " + riskCount);
        System.out.println("AGE");
        Evaluation evaluation = new Evaluation();

        if (riskCount == 0) {
            evaluation = new Evaluation(Risk.NONE);
        } else if (riskCount >= 2 && riskCount <= 5 && age >= 30) {
            evaluation = new Evaluation(Risk.BORDERLINE);
        } else if (age < 30 && riskCount >= 3 && gender.equals(Gender.MALE.toString())) {
            evaluation = new Evaluation(Risk.IN_DANGER);
        } else if (age < 30 && riskCount >= 4 && gender.equals(Gender.FEMALE.toString())) {
            evaluation = new Evaluation(Risk.IN_DANGER);
        } else if (age >= 30 && riskCount >= 6 && riskCount <= 7) {
            evaluation = new Evaluation(Risk.IN_DANGER);
        } else if (age < 30 && riskCount >= 5 && gender.equals(Gender.MALE.toString())) {
            evaluation = new Evaluation(Risk.EARLY_ONSET);
        } else if (age < 30 && riskCount >= 7 && gender.equals(Gender.MALE.toString())) {
            evaluation = new Evaluation(Risk.EARLY_ONSET);
        } else if (age >= 30 && riskCount >= 8) {
            evaluation = new Evaluation(Risk.EARLY_ONSET);
        }

        System.out.println(evaluation);
        return evaluation;
    }

    private Map<String, Integer> transformListToMap() {

        return IntStream.range(0, stringList.size())
                .boxed()
                .collect(Collectors.toMap(stringList::get, i -> i));
    }

    private int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();

    }

}
