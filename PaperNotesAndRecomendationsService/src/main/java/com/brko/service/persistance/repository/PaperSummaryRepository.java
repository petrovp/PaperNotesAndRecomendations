package com.brko.service.persistance.repository;

import com.brko.service.persistance.datamodel.PaperSummary;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ppetrov on 9/24/2016.
 */
public interface PaperSummaryRepository extends MongoRepository <PaperSummary, String> {

}
