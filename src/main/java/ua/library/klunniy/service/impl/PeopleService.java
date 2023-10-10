package ua.library.klunniy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Service
//@Transactional
//@RequiredArgsConstructor
public class PeopleService  {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
      return   peopleRepository.findAll();
 //       return peopleDao.index();
//        return peopleRepositoryJPA.findAll();
    }


    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
//        return peopleDao.show(id);
 //       return peopleRepositoryJPA.findById(id).get();
    }


    public void save(Person person) {
//        peopleDao.save(person);
//        peopleRepositoryJPA.save(person);
        peopleRepository.save(person);
    }


    public void update(long id, Person person) {
 //       peopleDao.update(id, person);
 //       peopleRepositoryJPA.save(person);
//        peopleRepositoryCRUD.s(id, person);
    }


    public void delete(long id) {
 //       peopleDao.delete(id);
//        peopleRepositoryJPA.deleteById(id);
    }

}
