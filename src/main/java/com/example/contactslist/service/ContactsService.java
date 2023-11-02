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
public class ContactsService implements InDSService {

    private final InDBRepository repository;

    @Override
    public List<Contacts> getAll() {
        return null;
    }
}
