package cz.uhk.kppro.fs.termwork.fslibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
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
		theModel.addAttribute("copy",pc);		
		return "copies-form";
	}
	
}
