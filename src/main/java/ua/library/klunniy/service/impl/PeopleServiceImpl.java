package ua.library.klunniy.service.impl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import ua.library.klunniy.dao.repository.PeopleRepositoryCRUD;
import ua.library.klunniy.dao.PeopleDao;
import ua.library.klunniy.dao.repository.PeopleRepositoryJPA;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.service.PeopleService;

import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Service
//@Transactional
//@RequiredArgsConstructor
public class PeopleServiceImpl implements PeopleService {

    //    private PeopleRepositoryJPA peopleRepositoryJPA;
//    private final PeopleRepositoryCRUD peopleRepositoryCRUD;
    private final PeopleDao peopleDao;

    public PeopleServiceImpl(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Override
    public List<Person> index() {
        return peopleDao.index();
//        return peopleRepositoryJPA.findAll();
    }

    @Override
    public Person show(long id) {
        return peopleDao.show(id);
 //       return peopleRepositoryJPA.findById(id).get();
    }

    @Override
    public void save(Person person) {
        peopleDao.save(person);
//        peopleRepositoryJPA.save(person);
    }

    @Override
    public void update(long id, Person person) {
        peopleDao.update(id, person);
 //       peopleRepositoryJPA.save(person);
//        peopleRepositoryCRUD.s(id, person);
    }

    @Override
    public void delete(long id) {
        peopleDao.delete(id);
//        peopleRepositoryJPA.deleteById(id);
    }

}
