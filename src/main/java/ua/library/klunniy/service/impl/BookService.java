package ua.library.klunniy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.repositories.BookRepository;
import ua.library.klunniy.model.Book;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    public List<Book> findAll(int page, int size) {
        return bookRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Book> findAll(String year) {
        return bookRepository.findAll(Sort.by(year));
    }

    public List<Book> findAll(int page, int size, String year) {
        return bookRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, year))).getContent();
    }

    public List<Book> findByBookNameStartingWith(String name) {
        return bookRepository.findByBookNameStartingWith(name);
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
    public void releaseTheBookFromTheAuthor(int id, Book updateBook) {
        updateBook.setBookId(id);
        updateBook.setTakeAt(null);
        bookRepository.save(updateBook);
    }

    @Transactional
    public void assignBookToAnAuthor(int id, Book updateBook) {
        updateBook.setBookId(id);
        updateBook.setTakeAt(new Date());
        bookRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public List<Book> findAllBooksByPerson(Person person) {
        List<Book> allBooksByPerson = bookRepository.findAllByOwner(person);
        for (Book book : allBooksByPerson) {
            Date date = book.getTakeAt();
            book.setOverdue(overdue(date));
        }
        return allBooksByPerson;
    }

    private String overdue(Date date) {
        if (date == null) {
            return "Y";
        }

        long diff = Math.abs(System.currentTimeMillis() - date.getTime());
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        System.out.println("Days: " + days);
        if (days >= 10) {
            System.out.println("Прошло больше 10 дней");
            return "Y";
        } else {
            System.out.println("Прошло меньше 10 дней");
            return null;
        }
    }

}
