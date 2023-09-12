package ua.library.klunniy.controller.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.library.klunniy.dao.PeopleDao;
import ua.library.klunniy.model.Person;

/**
 * @author Serhii Klunniy
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PeopleDao personDao;

    @Autowired
    public AdminController(PeopleDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", personDao.index());
        return "people/adminPage";
    }

    //todo
    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getPerson_id());
        System.out.println(person.getName());
        return "redirect:/people";
    }
}
