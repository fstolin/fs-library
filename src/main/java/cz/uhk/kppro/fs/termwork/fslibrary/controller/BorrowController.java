package cz.uhk.kppro.fs.termwork.fslibrary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.kppro.fs.termwork.fslibrary.dto.BorrowBookDTO;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;
import cz.uhk.kppro.fs.termwork.fslibrary.service.BookService;
import cz.uhk.kppro.fs.termwork.fslibrary.service.CustomerService;

@Controller
@RequestMapping("/borrowings")
public class BorrowController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/showBorrowFormCust")
	public String showBorrowForm(	Model theModel,
									@RequestParam("customerId") int id) {
		// pass as List -> modality with customer borrow form
		// and add a customer with customerId to the list
		List<Customer> customers = new ArrayList<>();
		customers.add(customerService.getCustomer(id));
		
		List<BookDetails> booksToBorrow = bookService.getAvailablePublications();
		BorrowBookDTO dto = new BorrowBookDTO();
		dto.setBookId(0);
		dto.setCustomerId(id);

		theModel.addAttribute("customers", customers);
		theModel.addAttribute("books", booksToBorrow);
		theModel.addAttribute("borrowedId", dto);
		
		return "borrow-form";
	}
	
	@GetMapping("/showBorrowFormBook")
	public String showBorrowFormBook(	Model theModel,
										@RequestParam("bookId") int id) {
		// pass as List -> modality with customer borrow form
		// and add a book DetailID to the list
		List<BookDetails> booksToBorrow = new ArrayList<>();
		booksToBorrow.add(bookService.getBookDetailsById(id));
		
		List<Customer> customers = customerService.getCustomers();
		BorrowBookDTO dto = new BorrowBookDTO();
		dto.setBookId(id);
		dto.setCustomerId(0);

		theModel.addAttribute("customers", customers);
		theModel.addAttribute("books", booksToBorrow);
		theModel.addAttribute("borrowedId", dto);
		
		return "borrow-form";
	}
	
	@PostMapping("/saveBorrowing")
	public String saveBorrowing(	@ModelAttribute("customer") Customer customer,
									@ModelAttribute("borrowedId") BorrowBookDTO dto) {
		// customer ID + DetailBook id
		Customer cuztomer = customerService.getCustomer(dto.getCustomerId());
		int physicalCopyID = bookService.setBorrowedByDetailsId(dto.getBookId());
		// Sanity check - this shoudl never happen
		if (physicalCopyID==0) throw new RuntimeException("PhyisicalCOpyID equals 0 -> that would mean, that user tried to borrow a borrowed / unexisting book");
		// Get the real physical copy
		PhysicalCopy copy = bookService.getBookCopy(physicalCopyID);
		// Debug log
		System.out.println(">> Inside saveBorrowing copy: " + copy);
		// Perform the borrowing
		customerService.addCustomerBorrowing(cuztomer.getId(), copy);		
		return "redirect:/customer/showCustomerDetail?customerId=" + cuztomer.getId() + "&success=true";
	}
	
	@GetMapping("/returnBook")
	public String returnBook (@RequestParam("copyId") int copyId, Model theModel) {
		// Temporary implementation, Many to Many relation is scalable for history etc. Currently not implemented
		int customerId = bookService.getFirstBorrower(copyId);
		PhysicalCopy copy = bookService.getBookCopy(copyId);
		customerService.returnCustomerBorrowing(customerId, copy);
		return ("redirect:/copies/list");
	}
	
	@GetMapping("/list")
	public String showBorrowingList(Model theModel) {
		List<Customer> customers = customerService.getCustomers();
		List<PhysicalCopy> borrowedCopies = bookService.getAllBorrowedPhysicalCopies();
		
		return "borrow-list";
	}
	
	
	

}
