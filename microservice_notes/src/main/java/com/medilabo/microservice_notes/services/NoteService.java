package com.medilabo.microservice_notes.services;

import com.medilabo.microservice_notes.exceptions.NoteNotFoundException;
import com.medilabo.microservice_notes.models.Note;
import com.medilabo.microservice_notes.repositories.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository notesRepository;

    public NoteService(NoteRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public Note addNote(Note note) {
        return notesRepository.insert(note);
    }

    public Note findNoteById(String id) throws NoteNotFoundException {
        Optional <Note> notesOptional = notesRepository.findById(id);
        return notesOptional.orElseThrow(() -> new NoteNotFoundException("Note does not exist;"));
    }

    public List <Note> findAllNote() {
        return notesRepository.findAll();
    }

    public void deleteNoteById(String id) throws NoteNotFoundException {
        Optional <Note> optionalNote = notesRepository.findById(id);
        if (optionalNote.isPresent()) {
            notesRepository.deleteById(id);
            return;
        }
        throw new NoteNotFoundException("Note with ID: " + id + " does not exist.");
    }

    public Note updateNoteById(String id, Note note) throws NoteNotFoundException {
        Optional <Note> optionalNote = notesRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note noteToUpdate = optionalNote.get();
            noteToUpdate.setNote(note.getNote());
            return notesRepository.save(noteToUpdate);
        }
        throw new NoteNotFoundException("Note with ID: " + id + " does not exist.");
    }

    public List<Note> findNoteByPatientId(String patientId) {
        return notesRepository.findNoteByPatientIdEquals(patientId);
    }

}
