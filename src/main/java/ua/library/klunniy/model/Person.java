package ua.library.klunniy.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person {

    long person_id;

    List<Book> bookList = new ArrayList<>();

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


}
