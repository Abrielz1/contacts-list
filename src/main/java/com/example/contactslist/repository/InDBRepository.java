package com.example.contactslist.repository;

import com.example.contactslist.entity.Contacts;

import java.util.List;

public interface InDBRepository {

    List<Contacts> getAll();
}
