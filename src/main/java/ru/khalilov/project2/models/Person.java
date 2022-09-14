package ru.khalilov.project2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int personId;
    @Column(name = "full_name")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]*([-][А-ЯЁ][а-яё]*)?\\s[А-ЯЁ][а-яё]*\\s[А-ЯЁ][а-яё]*$", message = "ФИО должно соответствовать образцу: Иванов Иван Иванович")
    @Size(min = 3, max =100, message = "ФИО должно содержать от 3 до 100 символов")
    private String fullName;
    @Min(value = 1900, message = "Введите действительную дату рождения")
    @Max(value = 2022, message = "Введите действительную дату рождения")
    @Column(name = "birthday_date")
    private int birthdayDate;
    @OneToMany(mappedBy = "owner")
    private List <Book> books;

  }
