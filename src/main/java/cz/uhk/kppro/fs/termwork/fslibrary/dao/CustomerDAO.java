package cz.uhk.kppro.fs.termwork.fslibrary.dao;

import java.util.List;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;

public interface CustomerDAO {
	
	public List<Customer> getCustomers();
	
	public void addCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);

}
