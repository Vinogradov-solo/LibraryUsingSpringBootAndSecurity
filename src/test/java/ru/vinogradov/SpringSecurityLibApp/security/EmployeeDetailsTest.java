package ru.vinogradov.SpringSecurityLibApp.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vinogradov.SpringSecurityLibApp.models.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class EmployeeDetailsTest {

    private Employee employee;
    private EmployeeDetails employeeDetails;

    @BeforeEach
    public void init() {
        employee = Employee.builder()
                .id(1)
                .username("TestName")
                .password("test")
                .role("ROLE_USER")
                .build();
        employeeDetails = new EmployeeDetails(employee);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals("test", employeeDetails.getPassword());
        assertEquals("TestName", employeeDetails.getUsername());
        // TODO another methods
    }
}
