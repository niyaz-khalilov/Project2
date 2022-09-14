package ru.khalilov.project2.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.khalilov.project2.models.Person;
import ru.khalilov.project2.services.PersonService;

@Component
public class PersonUtil implements Validator {

    PersonService personService;

    @Autowired
    public PersonUtil(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
       if (personService.checkPersonExisting(person.getFullName()) != null)
           errors.rejectValue("fullName", "", "Такой пользователь уже существует");
    }
}
