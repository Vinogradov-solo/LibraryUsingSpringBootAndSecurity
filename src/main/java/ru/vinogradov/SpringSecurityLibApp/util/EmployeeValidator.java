package ru.vinogradov.SpringSecurityLibApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vinogradov.SpringSecurityLibApp.models.Employee;
import ru.vinogradov.SpringSecurityLibApp.services.EmployeeDetailsService;

@Component
public class EmployeeValidator implements Validator {

    private final EmployeeDetailsService employeeDetailsService;

    @Autowired
    public EmployeeValidator(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;

        try {
            employeeDetailsService.loadUserByUsername(employee.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return; // не самый хороший стиль кода, в идеале надо создать класс EmployeeService и в нем Optional метод загрузки пользователя, но пока так...
        }
        errors.rejectValue("username", "", "Employee with same name is already exist!");
    }
}
