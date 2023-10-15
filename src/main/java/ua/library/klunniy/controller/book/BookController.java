package ua.library.klunniy.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.library.klunniy.dto.BookDTO;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;

import ua.library.klunniy.service.impl.BookService;
import ua.library.klunniy.service.impl.PeopleService;
import ua.library.klunniy.utils.BookDTOList;
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

    private final BookDTOList bookDTOList;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService, BookDTOList bookDTOList) {
        this.bookService = bookService;
//        this.bookValidator = bookValidator;
//        this.peopleService = peopleService;
        this.peopleService = peopleService;
        this.bookDTOList = bookDTOList;
    }

    @GetMapping()
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer books_per_page,
                        @RequestParam(value = "sort_by_year", required = false) String sort_by_year) {
        if (page != null && books_per_page != null && Boolean.parseBoolean(sort_by_year)) {
            model.addAttribute("book", bookService.findAll(page, books_per_page, "year"));
        } else if (page != null && books_per_page != null) {
            model.addAttribute("book", bookService.findAll(page, books_per_page));
        } else if (Boolean.parseBoolean(sort_by_year)) {
            model.addAttribute("book", bookService.findAll("year"));
        } else {
            model.addAttribute("book", bookService.findAll());
        }
        return "book/books";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("book") Book book) {
        return "book/search";
    }

    @PostMapping("/search")
    public String search2(@ModelAttribute("book") Book book,
                          @ModelAttribute("bookDTO") Book bookDTO,
                          Model model) {
        String name = book.getBookName();
        List<Book> bookList = bookService.findByBookNameStartingWith(name);

        model.addAttribute("found", "true");
        if (bookList.size() == 0) {

        } else {
            model.addAttribute("foundNot", "true");
            List<BookDTO> bookDTOLists = bookDTOList.getBookDTOList(bookList);
            model.addAttribute("bookDTOList", bookDTOLists);
        }
        return "book/search";
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
        bookService.releaseTheBookFromTheAuthor(id, book);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/addBook/{id}")
    public String makeAdmin(@ModelAttribute("person") Person person, @PathVariable(value = "id",
            required = false) int id) {
        Book book = bookService.findOne(id);
        book.setOwner(person);
        bookService.assignBookToAnAuthor(id, book);
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
        bookService.assignBookToAnAuthor(id, book);
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
