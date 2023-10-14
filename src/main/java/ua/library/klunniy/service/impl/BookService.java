package ua.library.klunniy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.repositories.BookRepository;
import ua.library.klunniy.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return foundBook.orElse(null);
    }

    public List<Book> listShow(Person person) {
        return bookRepository.findAllByOwner(person);
    }

    //
//    public Book findOne(String name) {
//        return bookRepository.findByBookName(name);
//    }
//
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setBookId(id);
        bookRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAllByOwner(Person person) {
        return bookRepository.findAllByOwner(person);
    }

}