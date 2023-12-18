package com.medilabo.microservice_notes;

import com.medilabo.microservice_notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceNotesApplication implements CommandLineRunner {

    private final NoteService noteService;

    @Autowired
    public MicroserviceNotesApplication(NoteService noteService) {
        this.noteService = noteService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceNotesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //System.out.println(noteService.findAllNote());
    }
}
