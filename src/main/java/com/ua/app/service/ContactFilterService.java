package com.ua.app.service;

import com.ua.app.repository.entity.Contact;

import java.util.List;

public interface ContactFilterService {
    List<Contact> filteringListByNotExistedRegex(int page, String regex);
}
