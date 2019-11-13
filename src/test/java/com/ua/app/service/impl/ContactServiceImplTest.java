package com.ua.app.service.impl;

import com.ua.app.repository.ContactRepository;
import com.ua.app.repository.entity.Contact;
import com.ua.app.service.ContactService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactServiceImplTest {

    @Mock
    private ContactRepository repository;

    private ContactService contactService;
    private List<Contact> contacts =  new ArrayList<>();
    private PageRequest pageRequest = new PageRequest(0,(COUNT_PAGE));
    private Page<Contact> page;
    private Contact bad = new Contact(1,"Bad");
    private Contact man = new Contact(2,"Man");

    static private int COUNT_PAGE = 4;

    @Before
    public void setUp() throws Exception {
        contactService = new ContactServiceImpl(repository);
        ReflectionTestUtils.setField(contactService,"numberOfRowsPerPage", COUNT_PAGE);

        contacts.add(bad);
        contacts.add(man);
        page = new PageImpl<>(contacts);
    }

    @After
    public void tearDown() throws Exception {
        contacts.clear();
    }

    @Test
    public void getContactsByPage() throws Exception {
        when(repository.findAll(pageRequest)).thenReturn(page);
        assertThat(contactService.getContactsByPage(0)).contains(man);
    }

    @Test
    public void getPageContacts() throws Exception {
        when(repository.count()).thenReturn(4L);
        assertThat(contactService.getPageContacts()).isGreaterThan(0);
    }
}