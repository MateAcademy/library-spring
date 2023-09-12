package ua.library.klunniy.service;

import ua.library.klunniy.model.Person;

import java.util.List;

/**
 * @author Serhii Klunniy
 */
public interface PeopleService {

    List<Person> getPeople();

    Person show(long id);

    void save(Person person);

    void update(long id, Person person);

    void delete(long id);
}
