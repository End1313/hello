package com.ua.app.web.controller;

import com.ua.app.repository.entity.ListContact;
import com.ua.app.service.ContactFilterService;
import com.ua.app.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/hello")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactFilterService filterService;

    private ExecutorService executorService = Executors.newCachedThreadPool();

    @GetMapping("/contacts")
    public ResponseEntity<ResponseBodyEmitter> getContactsWithoutRegex(@RequestParam String nameFilter){
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        executorService.execute(() -> {
                IntStream.rangeClosed(0, contactService.getPageContacts()-1).forEach(index -> {
                    try {
                        emitter.send(new ListContact(filterService.filteringListByNotExistedRegex(index, nameFilter)));
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }
                });
                emitter.complete();
                }
                );
        return new ResponseEntity<>(emitter, HttpStatus.OK);
    }
}
