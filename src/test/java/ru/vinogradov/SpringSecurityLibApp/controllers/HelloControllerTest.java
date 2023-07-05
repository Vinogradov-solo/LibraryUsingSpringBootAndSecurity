package ru.vinogradov.SpringSecurityLibApp.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloController helloController;

    @Test
    public void testSayHello() {
        String response = helloController.sayHello();
        assertEquals("views/hello", response);
    }

    @Test
    public void testShowAdminPage() {
        String response = helloController.showAdminPage();
        assertEquals("views/admin", response);
    }

    @Test
    public void testShow() {
        // TODO
    }
}
