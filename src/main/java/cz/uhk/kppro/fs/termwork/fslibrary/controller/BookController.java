package cz.uhk.kppro.fs.termwork.fslibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;
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
	
	@GetMapping("/addPublication")
	public String showFormForAdd(Model theModel) {
		BookDetails book = new BookDetails();
		theModel.addAttribute("book",book);		
		return "book-form";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute BookDetails book) {
		bookService.saveBookDetials(book);
		return "redirect:/book/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateBook(Model theModel, @RequestParam("bookId") int bookId) {
		BookDetails book = bookService.getBookDetailsById(bookId);
		theModel.addAttribute("book", book);
		return "book-form";
	}
	
	@GetMapping("/deleteBook")
	public String deleteBook(@RequestParam int bookId) {
		bookService.deleteBookDetails(bookId);
		return "redirect:/book/list";
	}
	
	@RequestMapping("/showBookDetail")
	public String showBookDetail(	Model theModel, 
									@RequestParam("bookId") int bookId, 
									@RequestParam("failCopy") boolean failedCopy) {
		
	
		BookDetails book = bookService.getBookDetailsById(bookId);			
		theModel.addAttribute("book", book);
		System.out.println(failedCopy);
		theModel.addAttribute("failedCopy", failedCopy);
		return "book-detail";
	}
	
	@GetMapping("/borrow")
	public String borrowABook(Model theModel, @RequestParam("bookId") int bookId) {
		List<PhysicalCopy> copies = bookService.getAvailablePhysicalCopies(bookId);
		
		// no available copies -> disallow borrowing
		if (copies.size()<1) {
			return ("redirect:/book/showBookDetail?bookId=" + bookId + "&failCopy=true");
		} else {
			return ("redirect:/borrowings/showBorrowFormBook?bookId="+ bookId);
		}
	}
	
}
