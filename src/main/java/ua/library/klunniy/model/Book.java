package ua.library.klunniy.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Serhii Klunniy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    long bookId;

    long personId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    String bookName;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    String author;

//    @NotEmpty(message = "Year should not be empty")
//    @Size(min = 0, max = 2025, message = "Year should be between 0 and 2025")
    @Min(value = 0, message = "Year should be greater than 0")
    @Max(value = 2025, message = "Year should be less than 2025")
    int year;

    public Book(String name, String author, int year) {
        this.bookName = name;
        this.author = author;
        this.year = year;
    }

}
