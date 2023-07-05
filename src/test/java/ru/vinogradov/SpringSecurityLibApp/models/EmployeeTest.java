package ru.vinogradov.SpringSecurityLibApp.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

    Employee employee;

    @BeforeEach
    public void init() {
         employee = new Employee("TestName");
    }

    @Test
    public void testGettersAndSetters() {

        employee.setId(100);
        assertEquals(100, employee.getId());

        employee.setRole("ADMIN");
        assertEquals("ADMIN", employee.getRole());

        employee.setUsername("Test1Name");
        assertEquals("Test1Name", employee.getUsername());

        employee.setPassword("test");
        assertEquals("test", employee.getPassword());
    }
}
