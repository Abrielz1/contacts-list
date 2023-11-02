package com.example.contactslist.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContactsRepository implements InDBRepository {

    private final JdbcTemplate template;

    private final InDBRepository repository;
}
