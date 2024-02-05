package com.example.contactlist.repository;

import com.example.contactlist.mapper.ContactMapper;
import com.example.contactlist.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {

        String query = "SELECT * FROM contacts";
        List<Contact> contacts = jdbcTemplate.query(query, (ResultSet rs, int row) -> {
            Contact contact = new Contact();
            contact.setId(rs.getLong("id"));
            contact.setFirstName(rs.getString("firstName"));
            contact.setLastName(rs.getString("lastName"));
            contact.setEmail(rs.getString("email"));
            contact.setPhone(rs.getString("phone"));
            return contact;
        });
        return new ArrayList<>(contacts);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String query = "SELECT * FROM contacts WHERE id = ?";
        Contact contact = jdbcTemplate.queryForObject(query, new ContactMapper(), id);
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contacts (id, firstName, lastName, email,phone ) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        Contact existContact = findById(contact.getId()).orElse(null);
        if (existContact != null) {
            String sql = "UPDATE contacts SET firstName = ?,lastName = ?, email = ?, phone = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
            return contact;
        }
        throw new RuntimeException();
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
