package com.ua.app.service;

import com.ua.app.repository.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getContactsByPage(int page);
    int getPageContacts();
}

