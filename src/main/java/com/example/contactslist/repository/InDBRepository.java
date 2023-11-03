package com.example.contactslist.repository;

import com.example.contactslist.entity.Contacts;

import java.util.List;
import java.util.Optional;

public interface InDBRepository {

    List<Contacts> getAll();

    Contacts createContact(Contacts contact);

    Contacts editContacts(Long id, Contacts contact);

    Optional<Contacts> findById(Long id);

    void deleteContactById(Long id);
}
