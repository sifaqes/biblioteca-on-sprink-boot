package es.ua.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ua.biblioteca.model.Book;
import es.ua.biblioteca.service.IBookService;

@Controller
public class ControllerTM {
	
	@Autowired
    private IBookService bookService;
	
	@RequestMapping("/libros")
	public String libros(Model modelo) {
		
		List<Book> libros = bookService.findAll();

		modelo.addAttribute("libros", libros);
		return "biblioteca";
	}
	
	@RequestMapping("/hola")
	public String hola(Model modelo) {

		modelo.addAttribute("mensaje", "Hola Thymeleaf");
		return "hola";
	}
	
	@RequestMapping("/createBook")
	public String createBook(Model model) {
		model.addAttribute("book", new Book());
	    return "form";
	}
	
	@PostMapping("/createBook")
	public String createBook(@ModelAttribute Book book, Model model) {
		String result = bookService.create(book);
	    model.addAttribute("book", book);
	    model.addAttribute("result", result);
	    return "result";
	}
}
