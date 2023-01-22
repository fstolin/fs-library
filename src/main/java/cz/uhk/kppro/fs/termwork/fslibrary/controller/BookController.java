package cz.uhk.kppro.fs.termwork.fslibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	// need to inject the book service
	@Autowired
	private BookService bookService;
	
	@GetMapping("/list")
	public String listBookDetails(Model theModel) {		
		// Get customers from the DAO
		List<BookDetails> books = bookService.getAllBookDetails();
		// Add them to my model
		theModel.addAttribute("books", books);
		
		return "book-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		BookDetails book = new BookDetails();
		theModel.addAttribute("book",book);		
		return "book-form";
	}
	
	// Saving customer from form
	@PostMapping("/saveBookDetail")
	public String saveCustomer(@ModelAttribute("book") BookDetails book) {
		bookService.saveBookDetials(book);
		return "redirect:/books/list";
	}
	
	
	// TODO
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int theID,
									Model theModel) {
	
		return "cust-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id, Model theModel) {

		return "redirect:/customer/list";
	}
	
	@GetMapping("/showCustomerDetail")
	public String showCustomerDetail (@RequestParam("customerId") int id, Model theModel) {

		return "cust-detail";
	}
	
}
