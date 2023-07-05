package ru.vinogradov.SpringSecurityLibApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vinogradov.SpringSecurityLibApp.models.Book;
import ru.vinogradov.SpringSecurityLibApp.models.Person;
import ru.vinogradov.SpringSecurityLibApp.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {


    private final BooksRepository booksRepository;


    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    public List<Book> findAll() {
        return booksRepository.findAll();
    }



    public Book findOne(int id) {
        Optional<Book> foundPerson = booksRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public Optional<Book> getBookByName(String name) {
        booksRepository.findByName(name);
        return booksRepository.findByName(name);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.map(value -> Optional.ofNullable(value.getOwner())).orElse(null);
    }

    @Transactional
    public void provideBook(int id, Person selectedPerson) {
        Optional<Book> book = booksRepository.findById(id);
        book.ifPresent(value -> value.setOwner(selectedPerson));
    }

    @Transactional
    public void releaseBook(int id) {
        Optional<Book> book = booksRepository.findById(id);
        book.ifPresent(value -> value.setOwner(null));
    }
}
