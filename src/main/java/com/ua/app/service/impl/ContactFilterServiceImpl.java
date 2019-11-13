package com.ua.app.service.impl;

import com.ua.app.repository.entity.Contact;
import com.ua.app.service.ContactFilterService;
import com.ua.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Async
public class ContactFilterServiceImpl implements ContactFilterService {

    @Autowired
    private ContactService contactService;

    public ContactFilterServiceImpl(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public List<Contact> filteringListByNotExistedRegex(int page, String regex) {
        List<Contact> contacts = contactService.getContactsByPage(page);
        return contacts.stream().filter(contact -> !contact.getName().matches(regex)).collect(Collectors.toList());
    }
}
