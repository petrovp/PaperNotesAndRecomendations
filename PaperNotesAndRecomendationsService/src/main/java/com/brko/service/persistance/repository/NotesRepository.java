package com.brko.service.persistance.repository;

import com.brko.service.persistance.datamodel.Note;
import com.brko.service.persistance.datamodel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ppetrov on 11/4/2016.
 */
public interface NotesRepository extends MongoRepository <Note, String> {

    List<Note> findByCreatedBy(User user);

}
