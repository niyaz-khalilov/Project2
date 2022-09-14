package ru.khalilov.project2.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khalilov.project2.models.Book;
import ru.khalilov.project2.models.Person;
import ru.khalilov.project2.repositories.PersonRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> showAllPerson() {
        return personRepository.findAll();
    }

    public Person showPerson (int id) {
       return personRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public void update(Person person) {
        personRepository.save(person);
    }

    //Возможна проблема N+1, исправить
    public List<Book> getBooks(int id) {
        Optional<Person> person = personRepository.findBooksOfPerson(id).stream().findAny();
        if (person.isPresent()) {
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }
        /*Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks();
        } else {
            return Collections.emptyList();
        }*/
    }

    public Person checkPersonExisting(String string) {
     return personRepository.findPersonByFullName(string).orElse(null);
    }
}
