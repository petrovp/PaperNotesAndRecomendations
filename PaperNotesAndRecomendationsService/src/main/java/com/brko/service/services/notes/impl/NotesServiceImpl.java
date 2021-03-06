package com.brko.service.services.notes.impl;

import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.User;
import com.brko.service.persistance.repository.NotesRepository;
import com.brko.service.services.jobs.ComputationThreadCreator;
import com.brko.service.services.notes.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ppetrov on 11/4/2016.
 */
@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private ComputationThreadCreator computationThreadCreator;

    public void saveNote(String text, User user) {
        Note noteForSaving = new Note();
        noteForSaving.setText(text);
        noteForSaving.setCreatedBy(user);
        noteForSaving.setCreatedOn(new Date());

        notesRepository.save(noteForSaving);

        computationThreadCreator.runSuggestionsForUserOnAddedNote(user);
    }

    public void editNote(Note newOne) {
        Note oldOne = notesRepository.findOne(newOne.getId());

        oldOne.setText(newOne.getText());

        notesRepository.save(oldOne);
    }

    @Override
    public List<Note> getNotesByUserEmail(String email) {
        return notesRepository.findByCreatedBy_Email(email);
    }
}
