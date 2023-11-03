package com.example.contactslist.controller;

import com.example.contactslist.Create;
import com.example.contactslist.Update;
import com.example.contactslist.entity.Contacts;
import com.example.contactslist.service.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController("/")
@RequiredArgsConstructor
public class REST {

    private final ContactsService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contacts> getAll() {
        return service.getAll();
    }

    @GetMapping("contacts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contacts getContactById(@Positive @PathVariable Long id) {
       return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contacts createContact(@Validated(Create.class) @NotBlank @RequestBody Contacts contacts) {
        return service.createContact(contacts);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Contacts updateContact(@NotBlank @Validated(Update.class) @RequestBody Contacts contacts) {
        return service.editContact(contacts);
    }

    @DeleteMapping("contacts/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@Positive @PathVariable Long id) {
        service.deleteContactById(id);
    }
}
