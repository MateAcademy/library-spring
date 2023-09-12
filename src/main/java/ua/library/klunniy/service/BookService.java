package ua.library.klunniy.service;

import ua.library.klunniy.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
public interface BookService {

    List<Book> index();

    Book show(long id);

    List<Book> listShow(long personId);

    Optional<Book> show(String name);

    void save(Book book);

    void update(long id, Book book);

    void delete(long id);

}
