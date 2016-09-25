package com.brko.persistance.repository;

import com.brko.persistance.datamodel.Author;
import com.brko.persistance.datamodel.PaperSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ppetrov on 9/24/2016.
 */
public interface PaperSummaryRepository extends MongoRepository <PaperSummary, String> {

}
