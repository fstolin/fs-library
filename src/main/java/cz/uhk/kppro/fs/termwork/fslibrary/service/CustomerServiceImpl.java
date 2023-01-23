	package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.CustomerDAO;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;

// Implementation of customer service interface. Business logic goes here.
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO;
	
	@Autowired
	public CustomerServiceImpl(CustomerDAO theCustomerDAO) {
		customerDAO = theCustomerDAO;
	}
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {		
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		// Date of registration - only for new customers
		if (customer.getRegistrationDate() == null || customer.getRegistrationDate().isEmpty() || customer.getRegistrationDate().equals("")) {
			customer.setOriginalRegistrationDate();
		}
		customerDAO.addCustomer(customer);		
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerDAO.getCustomer(id);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerDAO.deleteCustomer(id);		
	}
	
	@Override
	@Transactional
	public void addCustomerBorrowing(int id, PhysicalCopy c) {
		// Get the customer who borrows
		Customer cust = getCustomer(id);
		// Borrow the physical copy
		cust.borrowPhysicalCopy(c);
		// Save the update
		customerDAO.addCustomer(cust);
	}

	@Override
	@Transactional
	public void returnCustomerBorrowing(int id, PhysicalCopy c) {
		Customer cust = getCustomer(id);
		cust.returnPhysicalCopy(c);
		c.setBorrowed(0);
		customerDAO.addCustomer(cust);
	}

	@Override
	public List<PhysicalCopy> getAllBorrowingsByID(int id) {
		// TODO Unimpl
		return null;
	}
	
}
