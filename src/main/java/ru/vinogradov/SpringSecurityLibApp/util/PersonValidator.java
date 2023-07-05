package ru.vinogradov.SpringSecurityLibApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vinogradov.SpringSecurityLibApp.models.Person;
import ru.vinogradov.SpringSecurityLibApp.services.PeopleService;

@Component
public class PersonValidator implements Validator
{
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;   //делаем даункаст, приводим к персон
        // так как ТОЧНО знем для какого класса валидатор

        // посмотреть есть ли человек с таким же именем в базе данных
        if(peopleService.getPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }
    }
}
