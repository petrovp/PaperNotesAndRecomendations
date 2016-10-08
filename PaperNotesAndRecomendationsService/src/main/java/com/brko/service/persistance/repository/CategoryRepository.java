package com.brko.service.persistance.repository;

import com.brko.service.persistance.datamodel.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ppetrov on 9/24/2016.
 */
public interface CategoryRepository extends MongoRepository <Category, String> {

}
