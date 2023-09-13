package ua.library.klunniy.controller.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.service.BookService;
import ua.library.klunniy.service.PeopleService;
import ua.library.klunniy.utils.BookValidator;
import ua.library.klunniy.utils.PersonValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    private final BookService bookService;

    //   private final BookValidator bookValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, BookService bookService) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.bookService = bookService;
    }

    //страница всех людей
    @GetMapping()
    public String index(Model model) {
        List<Person> people = peopleService.getPeople();
        for (Person p : people) {
            List<Book> listBookForPerson = bookService.listShow(p.getPerson_id());
            p.setBookList(listBookForPerson);
        }
        model.addAttribute("person", people);
        return "people/people";
    }

    //страница добавления человека
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    //перехожу со страницы всех людей по ссылке на страницу одного человека
    @GetMapping("/{id}")
    public String show(@PathVariable(value = "id", required = false) long id, Model model) {

        Person person = peopleService.show(id);
        model.addAttribute("person", person);

        List<Book> listBookForPerson = bookService.listShow(person.getPerson_id());
        person.setBookList(listBookForPerson);

        if (!listBookForPerson.isEmpty())
            model.addAttribute("list_book", listBookForPerson);

        return "people/show";
    }

    //страница изменения человека
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", peopleService.show(id));
        return "people/edit";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        //       personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

}
