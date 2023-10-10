package ua.library.klunniy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.library.klunniy.model.Person;


import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer>, CrudRepository<Person, Integer> {
//    List<Person> findByName(String name);
//
//    List<Person> findByNameOrderByAge(String name);
//
//    List<Person> findByEmail(String email);
//
//    List<Person> findByNameStartingWith(String startingWith);
//
//    List<Person> findByNameOrEmail(String name, String email);

}
