package com.ua.app.repository;

import com.ua.app.repository.entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact,Long> {
    Page<Contact> findAll(Pageable page);
    long count();
}
