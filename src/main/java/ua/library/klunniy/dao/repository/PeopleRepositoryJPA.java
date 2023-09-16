package ua.library.klunniy.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.library.klunniy.model.Person;

/**
 * @author Serhii Klunniy
 */
@Repository
public interface PeopleRepositoryJPA extends JpaRepository<Person, Long> {
}
