package ua.library.klunniy.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.library.klunniy.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Serhii Klunniy
 */
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getLong("book_id"));
        book.setPersonId(rs.getLong("person_id"));
        book.setBookName(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        return book;
    }
}
