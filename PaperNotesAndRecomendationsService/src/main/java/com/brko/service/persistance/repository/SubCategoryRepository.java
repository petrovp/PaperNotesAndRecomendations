package com.brko.service.persistance.repository;

import com.brko.service.persistance.datamodel.SubCatogory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ppetrov on 9/24/2016.
 */
public interface SubCategoryRepository extends MongoRepository <SubCatogory, String> {

}
