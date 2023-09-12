package ua.library.klunniy.service.impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.library.klunniy.dao.BookDao;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Component
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<Book> index() {
        return bookDao.index();
    }

    @Override
    public Book show(long id) {
        return bookDao.show(id);
    }

    @Override
    public List<Book> listShow(long personId) {
        return bookDao.listShow(personId);
    }

    @Override
    public Optional<Book> show(String name) {
        return bookDao.show(name);
    }

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public void update(long id, Book book) {
        bookDao.update(id, book);
    }

    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }

}
