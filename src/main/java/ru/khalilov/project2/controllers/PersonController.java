package ru.khalilov.project2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.khalilov.project2.services.PersonService;
import ru.khalilov.project2.models.Book;
import ru.khalilov.project2.models.Person;
import ru.khalilov.project2.util.PersonUtil;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final PersonUtil personUtil;

    @Autowired
    public PersonController(PersonService personService, PersonUtil personUtil) {
        this.personService = personService;
        this.personUtil = personUtil;
    }

    @GetMapping()
    public String peoplePage(Model model) {
        List<Person> people = personService.showAllPerson();
        model.addAttribute("people", people);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String personPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.showPerson(id));
        List<Book> books = personService.getBooks(id);
        if (books == null || books.size()==0) {
            model.addAttribute("notBook", books);
        } else {
            model.addAttribute("listOfBook", books);
        }
        return "people/show";
    }

    @GetMapping("/new")
    public String personAddPage (@ModelAttribute ("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String addPerson (@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personUtil.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        personService.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson (@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPersonPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.showPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String editPerson(@ModelAttribute @Valid Person person, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        personUtil.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        person.setPersonId(id);
        personService.update(person);
        return "redirect:/people";
    }

    @GetMapping("/search")
    public String bookSearching() {
        return "people/search";
    }

    @PostMapping("/search")
    public String makeSearch(@RequestParam("query") String query, Model model) {
        List<Person> people = personService.findByTittleLike(query);
        model.addAttribute("people", people);
        return "people/search";
    }
}
