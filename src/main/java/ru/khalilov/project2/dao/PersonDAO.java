/*
package ru.khalilov.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.khalilov.project1.models.Book;
import ru.khalilov.project1.models.Person;

import java.util.List;

@Service
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(fullName, birthdayDate) VALUES (?, ?)",
                person.getFullName(), person.getBirthdayDate());
    }

    public List<Person> showAllPerson() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person showPerson (int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE personId=?", new Object [] {id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE personId=?", id);
    }

    public void update(Person person) {
        jdbcTemplate.update("UPDATE person SET fullname=?, birthdaydate=? " +
                "WHERE personid=?", person.getFullName(), person.getBirthdayDate(), person.getPersonId());
    }

    public List<Book> getBooks(int id) {
       return jdbcTemplate.query("SELECT book.* FROM book " +
                "JOIN person USING (personid) WHERE personid=?", new Object[] {id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}*/
