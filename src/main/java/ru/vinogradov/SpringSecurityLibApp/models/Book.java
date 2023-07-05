package ru.vinogradov.SpringSecurityLibApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Author should be not empty")
    @Size(min = 2, max = 100, message = "Author should be between 2 and 100 characters")
    @Column(name = "author")
    private String author;

    @Min(value = 0, message = "Should be 0 and more (integer)")
    @Size(min = 1900, max = 2023, message = "Date should be between 1900 and 2023 years")
    @Column(name = "year")
    private int year;

    @ManyToOne()
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }
}
