package com.example.contactslist.controller;

import com.example.contactslist.entity.Contacts;
import com.example.contactslist.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/")
@RequiredArgsConstructor
public class REST {

    private final ContactsService service;

    @GetMapping("contacts/{id}")
    public Contacts getContactById(@PathVariable Long id) {
        System.out.println("service " + service.findById(id));
       return service.findById(id);
    }

    @PostMapping
    public Contacts createContact(@RequestBody Contacts contacts) {
        return service.createContact(contacts);
    }

    @PutMapping
    public Contacts updateContact(@RequestBody Contacts contacts) {
        return service.editContact(contacts);
    }
}
