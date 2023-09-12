package ua.library.klunniy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.library.klunniy.dao.PeopleDao;
import ua.library.klunniy.model.Person;

/**
 * @author Serhii Klunniy
 */
@Component
public class PersonValidator implements Validator {

    private final PeopleDao peopleDao;

    @Autowired
    public PersonValidator(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if (peopleDao.show(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}
