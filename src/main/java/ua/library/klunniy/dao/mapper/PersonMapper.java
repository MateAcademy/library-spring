//package ua.library.klunniy.dao.mapper;
//
//import org.springframework.jdbc.core.RowMapper;
//import ua.library.klunniy.model.Book;
//import ua.library.klunniy.model.Person;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * @author Serhii Klunniy
// */
//public class PersonMapper implements RowMapper<Person> {
//    @Override
//    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Person person = new Person();
//        person.setPerson_id(rs.getLong("person_id"));
//        person.setName(rs.getString("name"));
//        person.setAge(rs.getInt("age"));
//        return person;
//    }
//}
