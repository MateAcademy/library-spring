package ua.library.klunniy.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.library.klunniy.dao.PeopleDao;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.service.BookService;
import ua.library.klunniy.utils.BookValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final BookValidator bookValidator;

    private final PeopleDao personDao;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator, PeopleDao personDao) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
        List<Book> index = bookService.index();
        model.addAttribute("book", index);
        return "book/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(value = "id", required = false) long id, Model model,
                       @ModelAttribute("person") Person person) {
        Book show = bookService.show(id);
        model.addAttribute("book", show);
        List<Person> index = personDao.index();
        model.addAttribute("people", index);

        Long personId = show.getPersonId();
        if (personId != null) {
            model.addAttribute("personId", personId);
            model.addAttribute("list_book", personDao.show(personId));
        }
        return "book/show";
    }

    @PostMapping("/dellBook/{id}")
    public String dellAdmin(@PathVariable(value = "id", required = false) long id) {
        Book book = bookService.show(id);
        book.setPersonId(null);
        bookService.update(id, book);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/addBook/{id}")
    public String makeAdmin(@ModelAttribute("person") Person person, @PathVariable(value = "id", required = false) long id) {
        Book book = bookService.show(id);
        book.setPersonId(person.getPerson_id());
        bookService.update(id, book);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookService.show(id));
        return "book/edit";
    }

    // 7 при Patch запросе будут приходить обновленные данные для человека
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("info") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newInfo(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/books/new";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

}
