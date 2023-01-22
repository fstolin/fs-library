package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.CustomerDAO;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;

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
		if (customer.getRegistrationDate().equals("") || customer.getRegistrationDate().isEmpty() || customer.getRegistrationDate().equals(null)) {
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
	
	private String getCurrentDate() {
		Date currentDate = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
		return dateFormat.format(currentDate);
	}

}
