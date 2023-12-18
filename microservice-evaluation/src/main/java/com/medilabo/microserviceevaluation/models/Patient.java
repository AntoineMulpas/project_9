package com.medilabo.microserviceevaluation.models;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Patient {

    private LocalDate dob;
    
}
