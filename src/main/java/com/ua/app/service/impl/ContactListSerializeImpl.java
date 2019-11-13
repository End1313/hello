package com.ua.app.service.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ua.app.repository.entity.Contact;
import com.ua.app.repository.entity.ListContact;

import java.io.IOException;
import java.util.List;

public class ContactListSerializeImpl extends JsonSerializer<ListContact> {

    @Override
    public void serialize(ListContact listContacts,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException{
        List<Contact> contacts = listContacts.getContacts();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("contacts");
        jsonGenerator.writeStartArray();
        for (Contact contact : contacts) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("Contact");
            jsonGenerator.writeObject(contact);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
        }
    }
