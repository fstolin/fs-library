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

import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;
import cz.uhk.kppro.fs.termwork.fslibrary.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {		
		// Get customers from the DAO
		List<Customer> customers = customerService.getCustomers();
		// Add them to my model
		theModel.addAttribute("customers", customers);
		
		return "cust-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer",theCustomer);		
		return "cust-form";
	}
	
	// Saving customer from form
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		//System.out.println(">> Customer reg date:"  + customer.getRegistrationDate() + "Null: " + customer.getRegistrationDate().equals(null) + "empty: " + customer.getRegistrationDate().equals(""));
		Customer tempCustomer;
		if (customer.getId() != 0) {
			tempCustomer = customerService.getCustomer(customer.getId());
		} else {
			tempCustomer = new Customer();
		}
		tempCustomer.setFirstName(customer.getFirstName());
		tempCustomer.setLastName(customer.getLastName());
		tempCustomer.setEmail(customer.getEmail());
		
		customerService.saveCustomer(tempCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theID,
									Model theModel) {
		Customer theCustomer = customerService.getCustomer(theID);
		theModel.addAttribute("customer", theCustomer);		
		return "cust-form";
	}
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id, Model theModel) {
		customerService.deleteCustomer(id);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showCustomerDetail")
	public String showCustomerDetail (@RequestParam("customerId") int id, Model theModel, @Param("success") boolean success) {
		Customer cust = customerService.getCustomer(id);
		List<PhysicalCopy> borrowings = cust.getBorrowedCopies();
		theModel.addAttribute("customer", cust);
		theModel.addAttribute("borrowings", borrowings);
		theModel.addAttribute("successBorrowing", success);
		return "cust-detail";
	}
	
	@GetMapping("/borrow")
	public String showBorrowFormular(@RequestParam("customerId") int id, Model theModel) {		
		return "redirect:/borrowings/showBorrowFormCust?customerId=" + id;
	}
	
}
