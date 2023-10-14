package ua.library.klunniy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.library.klunniy.model.Person;
import ua.library.klunniy.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
        //       return peopleDao.index();
//        return peopleRepositoryJPA.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
//        return peopleDao.show(id);
        //       return peopleRepositoryJPA.findById(id).get();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setPersonId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
