package ua.library.klunniy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.model.Person;

import java.util.List;

/**
 * @author Serhii Klunniy
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

//    List<Book> findByOwnerPerson_id(int personId);
//
//    @Query("select b from Book b where b.bookName = ?1")
//    Book findByBookName(String name);

}
