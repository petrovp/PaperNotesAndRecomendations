package com.brko.web.api.controllers;

import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.services.notes.NotesService;
import com.brko.web.config.security.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by ppetrov on 11/3/2016.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/notes")
public class NotesController {

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private NotesService notesService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Note> getNotes() {
        User user = userProvider.getUser();
        List<Note> notesByUserEmail = notesService.getNotesByUserEmail(user.getEmail());
        sort(notesByUserEmail);
        return notesByUserEmail;
    }

    @RequestMapping(value = "/add_note", method = RequestMethod.POST)
    public List<Note> saveNote(@RequestBody String text) {
        User authenticatedUser = userProvider.getUser();

        notesService.saveNote(text, authenticatedUser);

        List<Note> notesByUserEmail = notesService.getNotesByUserEmail(authenticatedUser.getEmail());
        sort(notesByUserEmail);
        return notesByUserEmail;
    }

    @RequestMapping(value = "/edit_note", method = RequestMethod.POST)
    public List<Note> editNote(@RequestBody Note note) {
        notesService.editNote(note);

        User user = userProvider.getUser();
        List<Note> notesByUserEmail = notesService.getNotesByUserEmail(user.getEmail());
        sort(notesByUserEmail);
        return notesByUserEmail;
    }
}
