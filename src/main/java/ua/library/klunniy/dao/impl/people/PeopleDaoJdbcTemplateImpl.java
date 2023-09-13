package ua.library.klunniy.dao.impl.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.library.klunniy.dao.PeopleDao;
import ua.library.klunniy.dao.mapper.PersonMapper;
import ua.library.klunniy.model.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author Serhii Klunniy
 */
@Component
public class PeopleDaoJdbcTemplateImpl implements PeopleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person ORDER BY person_id",
                new PersonMapper());
    }

    @Override
    public Person show(long id) {
        return jdbcTemplate.query("SELECT * FROM person p left join book b on p.person_id = b.person_id WHERE p.person_id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    @Override
    public Optional<Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new Object[]{name},
                        new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(name, age) VALUES (?, ?)", person.getName(), person.getAge());
    }

    @Override
    public void update(long id, Person person) {
        jdbcTemplate.update("UPDATE person SET name=?, age=? WHERE person_id=?", person.getName(),
                person.getAge(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("DELETE FROM person where person_id=?", id);
    }

}
