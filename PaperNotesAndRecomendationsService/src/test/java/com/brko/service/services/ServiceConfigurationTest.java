package com.brko.service.services;

import com.brko.service.persistance.datamodel.Author;
import com.brko.service.persistance.repository.AuthorRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static  org.hamcrest.MatcherAssert.assertThat;

/**
 * Test class for Spring configuration.
 *
 * Created by Petre on 9/5/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceConfigurationTest extends AbstractServiceIntegrationTest{

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testConfiguration() {

    }

    @Test
    @Ignore
    public void shouldTestMongoDb() {
        Author author = new Author();
        author.setId("author2");
        author.setName("Petre Petrov");

        authorRepository.save(author);

        Author byName = authorRepository.findByName("Petre Petrov");
        assertThat(byName.getName(), equalTo("Petre Petrov"));
    }
}
