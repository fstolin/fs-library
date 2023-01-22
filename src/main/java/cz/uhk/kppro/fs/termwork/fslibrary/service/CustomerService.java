package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.util.List;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;

// Service interface for business logic
public interface CustomerService {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int id);

	public void deleteCustomer(int id);
	
}
