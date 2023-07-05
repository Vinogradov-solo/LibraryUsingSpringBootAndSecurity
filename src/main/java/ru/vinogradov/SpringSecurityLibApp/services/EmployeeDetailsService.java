package ru.vinogradov.SpringSecurityLibApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vinogradov.SpringSecurityLibApp.models.Employee;
import ru.vinogradov.SpringSecurityLibApp.models.Person;
import ru.vinogradov.SpringSecurityLibApp.repositories.EmployeeRepository;
import ru.vinogradov.SpringSecurityLibApp.repositories.PeopleRepository;
import ru.vinogradov.SpringSecurityLibApp.security.EmployeeDetails;

import java.util.Optional;

@Service
public class EmployeeDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByUsername(s);

        if (employee.isEmpty())
            throw new UsernameNotFoundException("Employee not found");

        return new EmployeeDetails(employee.get());
    }
}
