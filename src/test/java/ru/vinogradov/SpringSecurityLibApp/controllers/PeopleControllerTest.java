package ru.vinogradov.SpringSecurityLibApp.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PeopleControllerTest {

    @Autowired
    private PeopleController peopleController;

    @Test
    public void testIndex() {
        //TODO
    }
}
