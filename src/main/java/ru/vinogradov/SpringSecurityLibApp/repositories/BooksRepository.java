package ru.vinogradov.SpringSecurityLibApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vinogradov.SpringSecurityLibApp.models.Book;

import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
   // List<Book> findByOwner(Person person);
    Optional<Book> findByName(String name);
}
