package com.example.contactslist.repository;

import com.example.contactslist.entity.Contacts;
import com.example.contactslist.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Contacts createContact(Contacts contact) {

        final String sqlQuery = """
                INSERT INTO contacts(first_name, last_name, email, phone)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder generatedId = new GeneratedKeyHolder();

        template.update(connection  -> {

            final PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"id"});
            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getEmail());
            stmt.setString(4, contact.getPhone());

            return stmt;
        }, generatedId);

        log.info("Контакт с id {} отправлен", contact.getId());
       contact.setId(generatedId.getKey().longValue());

        return contact;
    }

    @Override
    public Contacts editContacts(Long id, Contacts contact) {

        final String sqlCheckQuery = """
                SELECT * FROM contacts WHERE id = ?
                """;

        SqlRowSet contactRows = template.queryForRowSet(sqlCheckQuery, id);

        if (!contactRows.next()) {
            log.warn("Контакт с идентификатором {} не найден.", id);
            throw new ObjectNotFoundException("Контакт не найден");
        }

        final String sqlQuery = """
UPDATE contacts SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?""";

        template.update(sqlQuery,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getId());

        log.info("Контакт с {} обновлен", contact.getId());
        return contact;
    }

    @Override
    public Optional<Contacts> findById(Long id) {

        final String sqlQuery = """
                SELECT * FROM contacts WHERE id = ?
                """;

        SqlRowSet contactRows = template.queryForRowSet(sqlQuery, id);

        if (!contactRows.next()) {
            System.out.println("Ooops!");
            log.warn("Контакт с идентификатором {} не найден.", id);
            throw new ObjectNotFoundException("Контакт не найден");
        }

        log.info("Контакт с id {} отправлен", id);

        return Optional.ofNullable(template.queryForObject(sqlQuery, this::makeContact, id));
    }

    @Override
    public void deleteContactById(Long id) {
        String sqlQuery  = """
               DELETE from  contacts WHERE id= ?
                """;
        log.info("Контакт c id {} удален", id);
        template.update(sqlQuery , id);
    }


    private Contacts makeContact(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");

        Contacts c = new Contacts(id, firstName, lastName, email, phone);

        System.out.println("c " + c);

        return c;
    }
}
