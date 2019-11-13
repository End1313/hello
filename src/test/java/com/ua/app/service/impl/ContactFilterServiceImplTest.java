package com.ua.app.service.impl;

import com.ua.app.repository.entity.Contact;
import com.ua.app.service.ContactFilterService;
import com.ua.app.service.ContactService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactFilterServiceImplTest {

    @Mock
    private ContactService contactService;

    private ContactFilterService filterService;

    private List<Contact> contacts =  new ArrayList<>();
    private Contact bad = new Contact(1,"Bad");
    private Contact man = new Contact(2,"Man");

    @Before
    public void setUp() throws Exception {
        filterService = new ContactFilterServiceImpl(contactService);
        contacts.add(bad);
        contacts.add(man);
    }

    @After
    public void tearDown() throws Exception {
        contacts.clear();
    }

    @Test
    public void filteringListByNotExistedRegex() throws Exception {
        when(contactService.getContactsByPage(0)).thenReturn(contacts);
        assertThat(filterService.filteringListByNotExistedRegex(0,"^B.*$")).contains(man);
        assertThat(filterService.filteringListByNotExistedRegex(0,"^B.*$")).doesNotContain(bad);
        assertThat(filterService.filteringListByNotExistedRegex(0,"^.*[a].*$")).isEmpty();

    }

}