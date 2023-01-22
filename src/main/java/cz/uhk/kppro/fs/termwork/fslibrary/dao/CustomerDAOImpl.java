package cz.uhk.kppro.fs.termwork.fslibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class CustomerDAOImpl implements CustomerDAO {
	
	private EntityManager entityManager;
	
	// inject entity manager
	@Autowired
	public CustomerDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	// get all customers
	@Override
	public List<Customer> getCustomers() {
		TypedQuery<Customer> theQuery = entityManager.createQuery("from Customer", Customer.class);
		return theQuery.getResultList();
	}
	
	// add or update a customer
	@Override
	public void addCustomer(Customer customer) {
		entityManager.merge(customer);
	}

	// get customer by id
	@Override
	public Customer getCustomer(int id) {
		return entityManager.find(Customer.class, id);
	}
	
	// delete customer by id
	@Override
	public void deleteCustomer(int id) {
		TypedQuery<Customer> theQuery = entityManager.createQuery("delete from Customer where id =:theID", Customer.class);
		theQuery.setParameter("theID", id);
		theQuery.executeUpdate();
	}

}
