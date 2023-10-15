package ua.library.klunniy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.library.klunniy.dto.BookDTO;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.repositories.PeopleRepository;
import ua.library.klunniy.service.impl.PeopleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Component
public class BookDTOList {

    private final PeopleService peopleService;

    @Autowired
    public BookDTOList(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public List<BookDTO> getBookDTOList(List<Book> bookList) {
        if (bookList == null) throw new NullPointerException();

        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            BookDTO bookDTO = new BookDTO(book.getBookId(), book.getBookName(), book.getAuthor(), book.getYear());

            Person ownerFromBook = book.getOwner();
            if (ownerFromBook == null) {
                bookDTO.setOwner("Книга свободна");
            } else {
                Person person = book.getOwner();
                Person one = peopleService.findOne(person.getPersonId());
                String str = "Книга сейчас у: " + one.getName();
                bookDTO.setOwner(str);
            }
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }
}
