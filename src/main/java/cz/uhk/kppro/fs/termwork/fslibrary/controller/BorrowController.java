package cz.uhk.kppro.fs.termwork.fslibrary.controller;

import java.util.List;

import javax.management.RuntimeErrorException;

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
		Customer customer = customerService.getCustomer(id);
		List<BookDetails> booksToBorrow = bookService.getAvailablePublications();
		BorrowBookDTO dto = new BorrowBookDTO();
		dto.setBookId(0);

		theModel.addAttribute("customer", customer);
		theModel.addAttribute("books", booksToBorrow);
		theModel.addAttribute("borrowedId", dto);
		
		return "borrow-form";
	}
	
	@PostMapping("/saveBorrowing")
	public String saveBorrowing(	@ModelAttribute("customer") Customer customer,
									@ModelAttribute("borrowedId") BorrowBookDTO dto) {
		// customer ID + DetailBook id
		int physicalCopyID = bookService.setBorrowedByDetailsId(dto.getBookId());
		// Sanity check - this shoudl never happen
		if (physicalCopyID==0) throw new RuntimeException("PhyisicalCOpyID equals 0 -> that would mean, that user tried to borrow a borrowed / unexisting book");
		// Get the real physical copy
		PhysicalCopy copy = bookService.getBookCopy(physicalCopyID);
		// Debug log
		System.out.println(">> Inside saveBorrowing copy: " + copy);
		// Perform the borrowing
		customerService.addCustomerBorrowing(customer.getId(), copy);
		
		
		return "redirect:/customer/showCustomerDetail?customerId=" + customer.getId() + "&success=true";
	}
	

}
