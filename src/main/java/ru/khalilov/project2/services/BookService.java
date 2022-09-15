package ru.khalilov.project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalilov.project2.models.Book;
import ru.khalilov.project2.models.Person;
import ru.khalilov.project2.repositories.BookRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> showAllBooks(Integer page, Integer count, String sortBy) {
        if ((page == null || count == null) && (sortBy == null || sortBy.equals("empty"))) {
            return bookRepository.findAll();
        } else if ((page == null || count == null) && (sortBy != null && !sortBy.equals("empty"))) {
            return bookRepository.findAll(Sort.by(sortBy));
        } else if ((page != null && count != null) && (sortBy == null || sortBy.equals("empty"))) {
            return bookRepository.findAll(PageRequest.of(page - 1, count)).getContent();
        } else {
            return bookRepository.findAll(PageRequest.of(page - 1, count, Sort.by(sortBy))).getContent();
        }
    }

    public Book showBook(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void assignBook(Person person, int bookId) {
        bookRepository.findById(bookId).ifPresent(book -> {
                    book.setOwner(person);
                    book.setRentDate(new Date());
                }
        );
    }

    @Transactional
    public void releaseBook(int bookId) {
        bookRepository.findById(bookId).ifPresent(book -> {
                    book.setOwner(null);
                    book.setRentDate(null);
                }
        );
    }

    public Person showOwner(int bookId) {
        return bookRepository.findById(bookId).map(Book::getOwner).orElse(null);
    }

    public List<Book> findByTittleLike(String query) {
        return bookRepository.findByTittleStartingWith(query);
    }
}
