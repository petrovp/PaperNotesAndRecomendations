package com.brko.service.persistance.repository;

import com.brko.service.persistance.datamodel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ppetrov on 9/24/2016.
 */
public interface UserRepository extends MongoRepository <User, String> {

}
