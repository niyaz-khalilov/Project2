package ru.khalilov.project2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;
    @Column(name = "author")
    @Size(min = 1, max = 100, message = "Имя автора должно содержать от 1 до 100 символов")
    @NotBlank(message = "ФИО автора не может быть пустым или занятым пробелами")
    private String author;
    @Column(name = "tittle")
    @NotBlank(message = "Название книги не может быть пустым или занятым пробелами")
    @Size(max = 100, message = "Имя автора должно содержать до 100 символов")
    private String tittle;
    @Column(name = "issue_year")
    @Min(value = 0, message = "Год выпуска должен быть не раньше 0 года")
    @Max(value = 2022, message = "Введите действительную дату рождения")
    private int issueYear;
    @Column(name = "date_of_rent")
    @Temporal(TemporalType.DATE)
    private Date rentDate;
    @Transient
    private boolean expired;
}
