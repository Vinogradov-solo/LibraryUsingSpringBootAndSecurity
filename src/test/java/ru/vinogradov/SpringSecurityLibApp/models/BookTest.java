package ru.vinogradov.SpringSecurityLibApp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookTest {

    Book book;

    @Mock
    Person person;

    @BeforeEach
    public void init() {
        book = org.mockito.Mockito.spy(Book.class);
    }

    @Test
    void testGettersAndSetters() {
        book.setName("War");
        assertEquals("War", book.getName());

        book.setAuthor("John Snow");
        assertEquals("John Snow", book.getAuthor());

        book.setId(10);
        assertEquals(10, book.getId());

        book.setYear(1999);
        assertEquals(1999, book.getYear());
    }

    @Test
    public void testGetOwner() {

        Person person1 = this.person;
        when(book.getOwner()).thenReturn(person1);

        assertEquals(person1, book.getOwner());
    }
}
