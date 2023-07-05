package ru.vinogradov.SpringSecurityLibApp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vinogradov.SpringSecurityLibApp.models.Book;
import ru.vinogradov.SpringSecurityLibApp.models.Person;
import ru.vinogradov.SpringSecurityLibApp.repositories.PeopleRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PeopleServiceTest {

    @Mock
    private PeopleRepository peopleRepository;

    @InjectMocks
    private PeopleService peopleService;

    private Person person;

    @BeforeEach
    public void setup() {
        person = Person.builder()
                .id(1)
                .name("John")
                .yearOfBirth(2000)
                .books(new ArrayList<>())
                .build();
    }

    @DisplayName("JUnit test for save() method")
    @Test
    public void givenPersonObject_whenSavePerson_thenReturnPersonObject() {
        given(peopleRepository.save(person)).willReturn(person);

        System.out.println(peopleRepository);
        System.out.println(peopleService);

        Person savedPerson = peopleService.save(person);

        System.out.println(savedPerson);

        assertThat(savedPerson).isNotNull();
    }

    @DisplayName("JUnit test for findAll() method")
    @Test
    public void givenPeopleList_whenFindAllPeople_thenReturnList() {
        Person person1 = Person.builder()
                .id(2)
                .name("Alex")
                .yearOfBirth(1999)
                .books(new ArrayList<>())
                .build();

        given(peopleRepository.findAll()).willReturn(List.of(person, person1));

        List<Person> people = peopleService.findAll();

        assertThat(people).isNotNull();
        assertThat(people.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAll() method (Negative scenario)")
    @Test
    public void givenEmptyPeopleList_whenFindAll_thenReturnEmptyList() {
        Person person1 = Person.builder()
                .id(2)
                .name("Alex")
                .yearOfBirth(1999)
                .books(new ArrayList<>())
                .build();

        given(peopleRepository.findAll()).willReturn(Collections.emptyList());

        List<Person> people = peopleService.findAll();

        assertThat(people).isEmpty();
        assertThat(people.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for findOne method")
    @Test
    public void givenPersonId_whenFindOne_thenReturnPersonObject() {
        given(peopleRepository.findById(1)).willReturn(Optional.of(person));

        Person savedPerson = peopleService.findOne(person.getId());

        assertThat(savedPerson).isNotNull();
    }

    @DisplayName("JUnit test for getPersonByName() method")
    @Test
    public void givenPerson_whenGetPersonByName_thenReturnPersonObject() {
        given(peopleRepository.findByName("John")).willReturn(Optional.of(person));

        Person savedPerson = peopleService.getPersonByName(person.getName()).get();

        assertThat(savedPerson).isNotNull();
    }

    @DisplayName("JUnit test for update() method")
    @Test
    public void givenPersonObject_whenUpdate_thenReturnUpdatedPerson() {
        List<Book> books = Collections.singletonList(new Book("Some Title", "Author`s Name", 2010));

        given(peopleRepository.save(person)).willReturn(person);

        person.setYearOfBirth(1995);
        person.setName("Jack");
        person.setBooks(books);

        Person updatedPerson = peopleService.update(1, person);

        assertThat(updatedPerson.getYearOfBirth()).isEqualTo(1995);
        assertThat(updatedPerson.getName()).isEqualTo("Jack");
        assertThat(updatedPerson.getBooks()).isEqualTo(books);
    }

    @DisplayName("JUnit test for delete() method")
    @Test
    public void givenPersonId_whenDelete_thenNothing() {
        int personId = 1;

        willDoNothing().given(peopleRepository).deleteById(personId);

        peopleService.delete(personId);

        verify(peopleRepository, times(1)).deleteById(personId);
    }
}
