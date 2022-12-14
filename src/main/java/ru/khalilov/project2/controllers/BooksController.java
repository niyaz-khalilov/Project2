package ru.khalilov.project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.khalilov.project2.services.BookService;
import ru.khalilov.project2.services.PersonService;
import ru.khalilov.project2.models.Book;
import ru.khalilov.project2.models.Person;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BooksController(BookService bookService, PersonService personDAO) {
        this.bookService = bookService;
        this.personService = personDAO;
    }

    @PostMapping("/increase")
    public String increasePage(Model model, final RedirectAttributes redirectAttributes) {
        Object page = (Integer) model.getAttribute("page");
        redirectAttributes.addFlashAttribute("page", page);
        return "redirect:/books";
    }

    @GetMapping()
    public String booksPage(Model model, @RequestParam(value = "page", required = false) Integer page,
                            @RequestParam(value = "sortBy", required = false) String sortBy) {
        if (page == null || page <= 0) {
            page = 1;
        }
        List<Book> books = bookService.showAllBooks(page, sortBy);
        model.addAttribute("books", books);
        model.addAttribute("page", page);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") int bookId, Model model, @ModelAttribute("person") Person personIn) {
        model.addAttribute("book", bookService.showBook(bookId));
        Person person = bookService.showOwner(bookId);
        if (person == null) {
            model.addAttribute("people", personService.showAllPerson());
        } else {
            model.addAttribute("owner", person);
        }
        return "books/show";
    }

    @GetMapping("/new")
    public String bookAddPage(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBookPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.showBook(id));
        return "books/edit";
    }


    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";
        book.setBookId(id);
        bookService.update(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assignBookToPeople(@ModelAttribute Person person, @PathVariable("id") int bookId) {
        bookService.assignBook(person, bookId);
        return "redirect:/books/" + bookId;
    }

    @PatchMapping("/{id}/release")
    public String releaseBookFromPeople(@PathVariable("id") int bookId) {
        bookService.releaseBook(bookId);
        return "redirect:/books/" + bookId;
    }
}
