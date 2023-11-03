package com.example.contactslist.service;

import com.example.contactslist.entity.Contacts;
import com.example.contactslist.repository.InDBRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactsService implements InDBService {

    private final InDBRepository repository;

    @Override
    public List<Contacts> getAll() {
        return repository.getAll();
    }

    @Override
    public Contacts createContact(Contacts contact) {
       return repository.createContact(contact);
    }

    @Override
    public Contacts editContact(Contacts contact) {

        Contacts contactFind = repository.findById(contact.getId()).orElse(null);

        System.out.println("contactFind " + contactFind);

        if (contactFind.getFirstName() != null) {
            contactFind.setFirstName(contact.getFirstName());
        }
        if (contactFind.getLastName() != null) {
            contactFind.setLastName(contact.getLastName());
        }
        if (contactFind.getEmail() != null) {
            contactFind.setEmail(contact.getEmail());
        }
        if (contactFind.getPhone() != null) {
            contactFind.setPhone(contact.getPhone());
        }

        return repository.editContacts (contactFind.getId(), contactFind);
    }



    @Override
    public Contacts findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void deleteContactById(Long id) {
        repository.deleteContactById(id);
    }
}
