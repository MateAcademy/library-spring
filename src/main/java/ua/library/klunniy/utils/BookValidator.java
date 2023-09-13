package ua.library.klunniy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.library.klunniy.dao.BookDao;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;

/**
 * @author Serhii Klunniy
 */
@Component
public class BookValidator implements Validator {

    private final BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;
        if (bookDao.show(book.getBookName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }

}
