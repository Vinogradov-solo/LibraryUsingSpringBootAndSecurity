package ru.vinogradov.SpringSecurityLibApp.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.vinogradov.SpringSecurityLibApp.models.Person;

import java.util.ArrayList;

@DataJpaTest
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    // BDD style tests

    @DisplayName("For save()")
    @Test
    public void givenPersonObject_whenSave_thenReturnSavedPerson() {
        Person person = Person.builder()
                .name("John")
                .yearOfBirth(2000)
                .books(new ArrayList<>())
                .build();

        Person savedPerson = peopleRepository.save(person);

        Assertions.assertNotNull(savedPerson);
        Assertions.assertNotNull(savedPerson.getId());
    }

    @DisplayName("For findById()")
    @Test
    public void givenPersonId_whenFindById_thenReturnSavedPerson() {
        Person person = Person.builder()
                .name("John")
                .yearOfBirth(2000)
                .books(new ArrayList<>())
                .build();

        Person savedPerson = peopleRepository.save(person);
        Person personDB = peopleRepository.findById(person.getId()).get();

        Assertions.assertNotNull(personDB);
    }
}
