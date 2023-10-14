package ua.library.klunniy.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;

import ua.library.klunniy.service.impl.BookService;
import ua.library.klunniy.service.impl.PeopleService;
//import ua.library.klunniy.utils.BookValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;
//  private final BookValidator bookValidator;
//  private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
//        this.bookValidator = bookValidator;
//        this.peopleService = peopleService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String index(Model model) {
        List<Book> index = bookService.findAll();
        model.addAttribute("book", index);
        return "book/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(value = "id", required = false) int id, Model model,
                       @ModelAttribute("person") Person person) {
        Book show = bookService.findOne(id);
        model.addAttribute("book", show);
        List<Person> index = peopleService.findAll();
        model.addAttribute("people", index);

        Person owner = show.getOwner();
        if (owner != null) {
            Integer personId = show.getOwner().getPersonId();
            if (personId != null) {
                model.addAttribute("personId", personId);
                model.addAttribute("list_book", peopleService.findOne(personId));
            }
        }
        return "book/show";
    }

    @PostMapping("/dellBook/{id}")
    public String dellAdmin(@PathVariable(value = "id", required = false) int id) {
        Book book = bookService.findOne(id);
        book.setOwner(null);
        bookService.update(id, book);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/addBook/{id}")
    public String makeAdmin(@ModelAttribute("person") Person person, @PathVariable(value = "id",
            required = false) int id) {
        Book book = bookService.findOne(id);
        book.setOwner(person);
        bookService.update(id, book);
        return "redirect:/books/{id}";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }

    // 7 при Patch запросе будут приходить обновленные данные для человека
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("info") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
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
//        bookValidator.validate(book, bindingResult);
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
