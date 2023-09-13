package ua.library.klunniy.dao.impl.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.library.klunniy.dao.BookDao;
import ua.library.klunniy.dao.mapper.BookMapper;
import ua.library.klunniy.model.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Component
public class BookDaoJdbcTemplateImpl implements BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Book> index() {
        //new BeanPropertyRowMapper
        return jdbcTemplate.query("SELECT * FROM book ORDER BY book_id", new BookMapper());
    }

    @Override
    public Book show(long id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id = ?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public List<Book> listShow(long personId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?", new Object[]{personId}, new BookMapper());
    }

    @Override
    public Optional<Book> show(String name) {
        return jdbcTemplate.query("SELECT * FROM book WHERE book_name = ?", new Object[]{name}, new BookMapper())
                .stream().findAny();
    }


    @Override
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(book_name, author, year) VALUES (?, ?, ?)", book.getBookName(),
                book.getAuthor(), book.getYear());
    }

    @Override
    public void update(long id, Book book) {
        jdbcTemplate.update("UPDATE book SET person_id = ?, book_name = ?, author = ?, year = ? WHERE book_id = ?", book.getPersonId(), book.getBookName(),
                book.getAuthor(), book.getYear(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id = ?", id);
    }

}
