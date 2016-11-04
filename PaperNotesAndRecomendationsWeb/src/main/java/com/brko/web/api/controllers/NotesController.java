package com.brko.web.api.controllers;

import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.services.notes.NotesService;
import com.brko.web.config.security.UserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ppetrov on 11/3/2016.
 */
@RestController
public class NotesController {

    @Autowired
    private UserProvider userProvider;

    @Autowired
    private NotesService notesService;

    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public List<Note> getNotes() {
        User user = userProvider.getUser();

        return notesService.getNotesByYUser(user);
    }

    @RequestMapping(value = "/add_note", method = RequestMethod.POST)
    public List<Note> saveNote(@RequestBody String text) {
        User user = userProvider.getUser();

        notesService.saveNote(text, user);

        return notesService.getNotesByYUser(user);
    }

    @RequestMapping(value = "/edit_note", method = RequestMethod.POST)
    public List<Note> editNote(@RequestBody Note note) {
        notesService.editNote(note);

        User user = userProvider.getUser();
        return notesService.getNotesByYUser(user);
    }

    
}
