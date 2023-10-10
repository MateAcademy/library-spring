package ua.library.klunniy.model;

//import lombok.*;
//import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */

//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer person_id;

    @OneToMany(mappedBy = "owner")
    List<Book> bookList;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 40, message = "Name should be between 2 and 30 characters")
    String name;

    @Min(value = 0, message = "Age should be greater than 0")
    @Max(value = 2025, message = "Age should be less than 2025")
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setOneBook(Book book) {
        if (this.bookList == null) {
            this.bookList = new ArrayList<>();
            if (book != null && book.getBookName() != null) {
                this.bookList.add(book);
            }
        } else {
            if (book != null && book.getBookName() != null) {
                this.bookList.add(book);
            }
        }
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(List<Book> bookList, String name, int age) {
        this.bookList = bookList;
        this.name = name;
        this.age = age;
    }

    public Person(Integer person_id, List<Book> bookList, String name, int age) {
        this.person_id = person_id;
        this.bookList = bookList;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

}
