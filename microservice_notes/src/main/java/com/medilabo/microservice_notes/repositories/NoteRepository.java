package com.medilabo.microservice_notes.repositories;

import com.medilabo.microservice_notes.models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List <Note> findNoteByPatientIdEquals(String patientId);

}
