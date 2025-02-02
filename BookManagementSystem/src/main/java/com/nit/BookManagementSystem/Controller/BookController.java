package com.nit.BookManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nit.BookManagementSystem.Entity.BookEntity;
import com.nit.BookManagementSystem.Service.BookService;

@Controller
public class BookController{
	@Autowired
	private BookService service;
	@GetMapping("/")
	public String start(Model model) {
	    return index(model);
	}
	@GetMapping("/BookApp")
	public String index(Model model) {
	    model.addAttribute("bookEntity", new BookEntity());
	    return "index";
	}
	@PostMapping("/sBook")
	public String saveBook(@ModelAttribute("bookEntity") BookEntity book, RedirectAttributes redirectAttributes) {
	    try {
	        service.saveBook(book);
	        redirectAttributes.addFlashAttribute("succMsg", "Book added successfully!");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
	    }
	    return "redirect:/book"; // Redirects to the form and clears fields
	}
	@GetMapping("/book")
	public String showForm(@ModelAttribute("bookEntity") BookEntity book) {
	    return "index";
	}
	@PostMapping("/bEdit")
	public String UpdateBook(@ModelAttribute("bookId") BookEntity book, Integer bookId, RedirectAttributes redirectAttributes) {
		try{
			service.updateBook(book, bookId);
			redirectAttributes.addFlashAttribute("succMsg", "Book Edited successfully!");
		}
		catch(Exception e) {
			redirectAttributes.addFlashAttribute("errMsg", e.getMessage());
		}
		return "redirect:/book";
	}
	@GetMapping("/books")
	public ModelAndView getBooks() {
		ModelAndView mav = new ModelAndView();
		List<BookEntity> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
	}
	@GetMapping("/delete")
	public ModelAndView deleteBook(@RequestParam("bookId") Integer bookId){
		service.deleteBook(bookId);
		ModelAndView mav = new ModelAndView();
		List<BookEntity> allBooks = service.getAllBooks();
		mav.addObject("books", allBooks);
		mav.setViewName("booksView");
		return mav;
	}
	@GetMapping("/edit")
	public ModelAndView editBook(@RequestParam("bookId") Integer bookId){
		BookEntity bookObj = service.getBookById(bookId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("bookEntity", bookObj);
		mav.setViewName("bookEdit");
		return mav;
	}
}
