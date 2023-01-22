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

import cz.uhk.kppro.fs.termwork.fslibrary.dto.BookDTO;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;
import cz.uhk.kppro.fs.termwork.fslibrary.service.BookService;

@Controller
@RequestMapping("/copies")
public class CopiesController {
	
	// need to inject the customer service
	@Autowired
	private BookService bookService;
	
	@GetMapping("/list")
	public String getCopiesList(Model theModel) {
		List<PhysicalCopy> copies = bookService.getAllBookCopies();
		theModel.addAttribute("copies", copies);
		return "copies-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		PhysicalCopy pc = new PhysicalCopy();
		List<BookDetails> details = bookService.getAllBookDetails();
		
		// DTO for detailID & Amount
		BookDTO dto = new BookDTO();
		dto.setDetails(details);
		// Default 1
		dto.setAmountOfCopies(1);
		
		theModel.addAttribute("copy",pc);
		theModel.addAttribute("bookDto",dto);

		return "copies-form";
	}
	
	@PostMapping("/saveCopies")
	public String saveCopies(	@ModelAttribute("copy") PhysicalCopy copy,
								@ModelAttribute("bookDTO") BookDTO dto
								) {
		copy.setBookDetails(bookService.getBookDetailsById(dto.getDetailId()));
		bookService.saveBookCopy(copy, dto.getAmountOfCopies());
		System.out.println(">>> Saved");
		return "redirect:/copies/list";
	}
	
	@GetMapping("/deleteCopy")
	public String deleteCustomer(@RequestParam("copyId") int id, Model theModel) {
		bookService.deleteBookCopy(id);
		return "redirect:/copies/list";
	}
	
}
