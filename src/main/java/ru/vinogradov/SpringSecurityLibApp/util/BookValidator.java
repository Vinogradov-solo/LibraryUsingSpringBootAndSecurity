package ru.vinogradov.SpringSecurityLibApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.vinogradov.SpringSecurityLibApp.models.Book;
import ru.vinogradov.SpringSecurityLibApp.services.BooksService;

@Component
public class BookValidator implements Validator
{
    private final BooksService booksService;

    @Autowired
    public BookValidator(BooksService booksService) {
        this.booksService = booksService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;   //делаем даункаст, приводим к Book
        // так как ТОЧНО знем для какого класса валидатор

        // посмотреть есть ли книга с таким же именем в базе данных
        if(booksService.getBookByName(book.getName()).isPresent()) {
            errors.rejectValue("name", "", "This name is already taken");
        }

        if(booksService.getBookByName(book.getAuthor()).isPresent()) {
            errors.rejectValue("author", "", "This author is already taken");
        }
    }
}
