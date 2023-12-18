package com.medilabo.microserviceevaluation.models;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Note {

    private String id;
    private String note;
    private String patientId;

}
