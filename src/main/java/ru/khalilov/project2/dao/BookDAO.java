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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAllBooks() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBook (int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE bookId=?", new Object [] {id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(tittle, author, issueYear) VALUES (?, ?, ?)", book.getTittle(), book.getAuthor(), book.getIssueYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE bookid=?", id);
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE book SET author=?, tittle=?, issueyear=? " +
                "WHERE bookid=?", book.getAuthor(), book.getTittle(), book.getIssueYear(), book.getBookId());
    }

    public void assignBook(Person personId, int bookId) {
        jdbcTemplate.update("UPDATE book SET personid = ? WHERE bookid= ?", personId, bookId);
    }

    public void releaseBook(int bookId) {
        jdbcTemplate.update("UPDATE book SET personid = ? WHERE bookid= ?", null, bookId);
    }

    public Person showOwner(int bookId) {
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person USING (personid) WHERE bookid=?",
                new Object[] {bookId}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
*/