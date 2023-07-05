package ru.vinogradov.SpringSecurityLibApp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonTest {

    Person person;

    @Mock
    Book book;

    @BeforeEach
    public void init() {
        person = org.mockito.Mockito.spy(Person.class);
    }

    @Test
    void testGettersAndSetters() {

        person.setId(20);
        assertEquals(20, person.getId());

        person.setName("Jane");
        assertEquals("Jane", person.getName());

        person.setYearOfBirth(1960);
        assertEquals(1960, person.getYearOfBirth());
    }

    @Test
    public void testGetBooks() {

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        when(person.getBooks()).thenReturn(bookList);

        assertEquals(bookList, person.getBooks());
    }
}
