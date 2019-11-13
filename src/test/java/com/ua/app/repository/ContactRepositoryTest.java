package com.ua.app.repository;

import com.ua.app.repository.entity.Contact;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class ContactRepositoryTest{
    private static final int CONTACT_COUNT = 5;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContactRepository repository;

    @Before
    public void setUp() throws Exception {
        entityManager.getEntityManager()
                .createNativeQuery("DROP TABLE IF EXISTS Contact;\n" +
                        "CREATE TABLE Contact(id INTEGER(64) PRIMARY KEY NOT NULL , name VARCHAR(256));")
                .executeUpdate();
        IntStream.rangeClosed(1, CONTACT_COUNT).forEach(index -> entityManager.persist(new Contact(index, "Name-" + index)));
    }

    @After
    public void tearDown() throws Exception {
        entityManager.clear();
    }

    @Test
    public void findAll() throws Exception {
        assertThat(repository.findAll(new PageRequest(0,CONTACT_COUNT)).getContent()).hasSize(CONTACT_COUNT);
    }
}