package ua.library.klunniy.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.library.klunniy.model.Book;
import ua.library.klunniy.service.BookService;
import ua.library.klunniy.utils.BookValidator;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Serhii Klunniy
 * /**
 * REST описывает то какие URLы и HTTP методы у нас должны быть для взаимодействия с данными
 *
 * С GET запросом вот по этому URL мы получим все записи:
 *  1 ----> GET /posts Получаем все записи(READ)
 *
 *  2 ----> GET /posts/:id Получаем одну запись(READ)
 *  3 ----> DELETE /posts/:id Удаляем запись(DELETE)
 *
 *  4 -----> GET /posts/new HTML форма создания записи
 *  5 -----> POST /posts Создаем новую запись(CREATE)
 *
 *  6 -----> GET /posts/:id/edit HTML форма редактирования записи
 *  7 -----> PATCH /posts/:id Обновляем запись(UPDATE)
 *
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookService bookService, BookValidator bookValidator) {
        this.bookService = bookService;
        this.bookValidator = bookValidator;
    }

    //при обращении на адрес: /admin/book я должен получить список из данных:
    //Получим всех (людей/информацию) из DAO и передам на отображение в представление
//GET /people  - показываем список из всех людей

    // 1
    @GetMapping
    public String index(Model model) {
        //fined books.html in DB
        List<Book> index = bookService.index();
        model.addAttribute("book", index);
//необходимо реализовать шаблон - он будет отображать список из книг
        return "book/books";
    }

    //при обращении на адрес: /admin/book/:id я должен получить конкретное какое-то данное:
//GET /people/:id  - показываем страницу одного человека
// 2
    @GetMapping("/{id}")
    public String show(@PathVariable(value = "id", required = false) long id, Model model) {
//Получим одного человека из DAO и передадим на отображение в представление
//необходимо реализовать шаблон - он будет отображать одного человека
        model.addAttribute("book", bookService.show(id));


        return "book/show";
    }

//GET /people/new  - будет в браузере отображется HTML форма для добавления одного человека / создания записи
//пишу контроллер который будет отдавать форму / шаблон для создания одного человека
//метод для получения новой информации / html форма для новой информации
//    @GetMapping("/new")
//    public String newInfo(Model model) {
//        model.addAttribute("book", new Info());
//        return "book/new";
//    }

    // 6, создаю форму для редактирования, этот метод будет возвращать HTML страницу для обновления /
// редактирования человека:
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookService.show(id));
        return "book/edit";
    }

    // 7 при Patch запросе будут приходить обновленные данные для человека
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("info") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

/*------ NEW ----------------------------------------------------------------------------------------------*/
    // 4 HTML форма создания записи
    @GetMapping("/new")
    public String newInfo(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/books/new";
        }

        bookService.save(book);
        return "redirect:/books";
    }

/*--------------DELETE---------------------------------------------------------------------*/

    // 3
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }


//    @PostMapping()
//    public String create(@RequestParam(value = "name", required = false) String name,
//                       @RequestParam(value = "text", required = false) String text,
//                       @RequestParam(value = "description", required = false) String description,
//                       Model model) {
//
//        Info book = new Info(name, text, description);
////добавляю инфо в бд
//        infoService.save(book);
//        return "redirect:/admin/book";
//    }

    //POST /people Будет добавляться один новый человек / Создаем новую запись(CREATE)
// 5

}
