package com.example.contactslist.service;

import com.example.contactslist.entity.Contacts;

import java.util.List;

public interface InDBService {
    List<Contacts> getAll();

    Contacts createContact(Contacts contact);

    Contacts editContact(Contacts contact);

    Contacts findById(Long id);

    void deleteContactById(Long id);
}
