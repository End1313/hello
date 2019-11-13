package com.ua.app.repository.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ua.app.service.impl.ContactListSerializeImpl;

import java.util.List;

@JsonSerialize(using = ContactListSerializeImpl.class)
public class ListContact {

    private List<Contact> contacts;

    public ListContact(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
