package com.medilabo.microservice_patient.repositories;

import com.medilabo.microservice_patient.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
