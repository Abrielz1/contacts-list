package com.example.contactslist.repository;

import com.example.contactslist.entity.Contacts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ContactsRepository implements InDBRepository {

    private final JdbcTemplate template;

    @Override
    public List<Contacts> getAll() {

        final String sqlQuery = "SELECT * FROM contacts";

        log.info("Список пользователей контактов отправлен");
        return template.query(sqlQuery, this::makeContact);
    }

    private Contacts makeContact(ResultSet resultSet, int rowNum) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");

        return new Contacts(id, firstName, lastName, email, phone);
    }
}
