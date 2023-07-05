package ru.vinogradov.SpringSecurityLibApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vinogradov.SpringSecurityLibApp.models.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}
