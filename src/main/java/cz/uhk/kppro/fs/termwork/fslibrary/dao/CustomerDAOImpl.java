package cz.uhk.kppro.fs.termwork.fslibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

// Customer DAO implementation
@Repository
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
		TypedQuery<Customer> theQuery = entityManager.createQuery("from Customer order by lastName", Customer.class);
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
		Query theQuery = entityManager.createQuery("delete from Customer where id =:theID");
		theQuery.setParameter("theID", id);
		theQuery.executeUpdate();
	}

}
