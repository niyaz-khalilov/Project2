package ru.khalilov.project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalilov.project2.models.Book;
import ru.khalilov.project2.models.Person;
import ru.khalilov.project2.repositories.BookRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public List<Book> showAllBooks(Integer page, String sortBy) {
       if (sortBy!=null) {
          return bookRepository.findAll(PageRequest.of(page, 2, Sort.by(sortBy))).getContent();
       } else {
           return bookRepository.findAll(PageRequest.of(page, 2)).getContent();
       }
    }

    public Book showBook (int id) {
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
        bookRepository.findById(bookId).ifPresent(book -> book.setOwner(person));
    }

    @Transactional
    public void releaseBook(int bookId) {
        bookRepository.findById(bookId).ifPresent(book -> book.setOwner(null));
    }

    public Person showOwner(int bookId) {
        return bookRepository.findById(bookId).map(Book::getOwner).orElse(null);
    }
}
