package com.ua.app.service.impl;

import com.ua.app.repository.ContactRepository;
import com.ua.app.repository.entity.Contact;
import com.ua.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Async
public class ContactServiceImpl implements ContactService {

    @Value("${util.value.numberOfRowsPerPage}")
    int numberOfRowsPerPage;

    @Autowired
    private ContactRepository contactsRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactsRepository = contactRepository;
    }

    @Override
    public List<Contact> getContactsByPage(int page) {
        return contactsRepository
            .findAll(new PageRequest(page, numberOfRowsPerPage))
            .getContent(); }

    @Override
    public int getPageContacts() {
        return (int)Math.ceil(contactsRepository.count()/numberOfRowsPerPage);
    }
}
