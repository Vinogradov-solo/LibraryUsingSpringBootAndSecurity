package ru.vinogradov.SpringSecurityLibApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vinogradov.SpringSecurityLibApp.models.Book;
import ru.vinogradov.SpringSecurityLibApp.models.Person;
import ru.vinogradov.SpringSecurityLibApp.repositories.PeopleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Person> getPersonByName(String name) {
        return peopleRepository.findByName(name);
    }

    @Transactional
    public Person save(Person person) {
        return peopleRepository.save(person);
    }

    @Transactional
    public Person update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        return peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public List<Book> getBooksByPersonId(int id) {
        Optional<Person> person = peopleRepository.findById(id);

        if(person.isPresent()) return person.get().getBooks();
         else return Collections.emptyList();
    }
}
