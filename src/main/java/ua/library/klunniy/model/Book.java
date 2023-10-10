package ua.library.klunniy.model;

//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Serhii Klunniy
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long bookId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    Person owner;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    String bookName;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    String author;

    @Min(value = 0, message = "Year should be greater than 0")
    @Max(value = 2025, message = "Year should be less than 2025")
    int year;

    public Book(String name, String author, int year) {
        this.bookName = name;
        this.author = author;
        this.year = year;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book(Person owner, String bookName, String author, int year) {
        this.owner = owner;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public Book(Long bookId, Person owner, String bookName, String author, int year) {
        this.bookId = bookId;
        this.owner = owner;
        this.bookName = bookName;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }
}
