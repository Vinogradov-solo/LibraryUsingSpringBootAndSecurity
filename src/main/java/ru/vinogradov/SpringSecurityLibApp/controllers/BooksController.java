package ru.vinogradov.SpringSecurityLibApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vinogradov.SpringSecurityLibApp.models.Book;
import ru.vinogradov.SpringSecurityLibApp.models.Person;
import ru.vinogradov.SpringSecurityLibApp.services.BooksService;
import ru.vinogradov.SpringSecurityLibApp.services.PeopleService;
import ru.vinogradov.SpringSecurityLibApp.util.BookValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;
    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
        return "views/books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", booksService.findOne(id));

        Optional<Person> bookOwner = booksService.getBookOwner(id);

        if(bookOwner.isPresent()) {
            model.addAttribute("owner", bookOwner.get());
        }
        else
            model.addAttribute("people", peopleService.findAll());

        return "views/books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "views/books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("books") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "views/books/new";

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findOne(id));
        return "views/books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "views/books/edit";

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        booksService.provideBook(id, selectedPerson);
        return "redirect:/books/" + id;
    }
}
