package ru.khalilov.project2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.khalilov.project2.models.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {
}
