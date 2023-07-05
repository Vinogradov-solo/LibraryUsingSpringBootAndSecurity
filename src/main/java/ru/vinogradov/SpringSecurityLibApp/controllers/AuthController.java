package ru.vinogradov.SpringSecurityLibApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vinogradov.SpringSecurityLibApp.models.Employee;
import ru.vinogradov.SpringSecurityLibApp.services.RegistrationService;
import ru.vinogradov.SpringSecurityLibApp.util.EmployeeValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final EmployeeValidator employeeValidator;
    private final RegistrationService registrationService;

    @Autowired
    public AuthController(EmployeeValidator employeeValidator, RegistrationService registrationService) {
        this.employeeValidator = employeeValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "views/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("employee") Employee employee) {
        return "views/auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("employee") @Valid Employee employee,
                                      BindingResult bindingResult) {
        employeeValidator.validate(employee, bindingResult); // Проверили, есть ли уже такой же в БД

        if(bindingResult.hasErrors())
            return "views/auth/registration";

        registrationService.register(employee); // Регистрируем

        return "redirect:views/auth/login";
    }
}
