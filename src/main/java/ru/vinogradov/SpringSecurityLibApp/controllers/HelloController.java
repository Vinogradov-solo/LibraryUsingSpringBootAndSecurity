package ru.vinogradov.SpringSecurityLibApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vinogradov.SpringSecurityLibApp.security.EmployeeDetails;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "views/hello";
    }

    @GetMapping("/show")
    public String show() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeeDetails employeeDetails = (EmployeeDetails) authentication.getPrincipal();
        System.out.println(employeeDetails.getEmployee());
        return "views/hello";
    }

    @GetMapping("/admin")
    public String showAdminPage() {
        return "views/admin";
    }
}
