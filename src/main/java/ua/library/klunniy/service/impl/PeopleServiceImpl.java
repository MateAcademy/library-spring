package ua.library.klunniy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.library.klunniy.dao.PeopleDao;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.service.PeopleService;

import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Component
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleDao peopleDao;

    @Override
    public List<Person> getPeople() {
        return peopleDao.index();
    }

    @Override
    public Person show(long id) {
        return peopleDao.show(id);
    }

    @Override
    public void save(Person person) {
        peopleDao.save(person);
    }

    @Override
    public void update(long id, Person person) {
        peopleDao.update(id, person);
    }

    @Override
    public void delete(long id) {
        peopleDao.delete(id);
    }

}
