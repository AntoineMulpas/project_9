package com.medilabo.microservice_patient.models;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Address")
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(allocationSize = 1, name = "address_sequence")
    private Long id;
    private String address;
    private String zip;
    private String city;

}
