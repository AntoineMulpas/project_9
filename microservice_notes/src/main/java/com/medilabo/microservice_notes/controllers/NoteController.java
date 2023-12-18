package com.medilabo.microservice_notes.controllers;

import com.medilabo.microservice_notes.exceptions.NoteNotFoundException;
import com.medilabo.microservice_notes.models.Note;
import com.medilabo.microservice_notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/note")
@CrossOrigin(origins = "*")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<Note> addNote(
            @RequestBody Note note
    ) {
        Note addedNote = noteService.addNote(note);
        return ResponseEntity.ok().body(addedNote);
    }

    @GetMapping("/all")
    public ResponseEntity<List <Note>> findAllNotes() {
        List <Note> allNote = noteService.findAllNote();
        return ResponseEntity.ok().body(allNote);
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteNoteById(
            @PathVariable String id
    ) {
        try {
            noteService.deleteNoteById(id);
            return ResponseEntity.ok().body("Note deleted with success.");
        } catch (NoteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note does not exist.");
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Note> updateNoteById(
            @PathVariable String id,
            @RequestBody Note note
    ) {
        try {
            Note updatedNote = noteService.updateNoteById(id, note);
            return ResponseEntity.ok().body(updatedNote);

        } catch (NoteNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<Note>> findNoteByPatientId(
            @PathVariable String id
    ) {
        List <Note> notes = noteService.findNoteByPatientId(id);
        return ResponseEntity.ok().body(notes);
    }

}
