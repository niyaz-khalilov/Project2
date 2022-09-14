package ru.khalilov.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khalilov.project2.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository <Person, Integer> {
    Optional<Person> findPersonByFullName(String string);

    @Query("From Person p left JOIN FETCH p.books where p.personId = ?1")
    List <Person> findBooksOfPerson(int id);
}
