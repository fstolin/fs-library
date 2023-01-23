package cz.uhk.kppro.fs.termwork.fslibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.CustomerDAOImpl;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import jakarta.persistence.EntityManager;

@DataJpaTest
class CustomerDAOImplTest {
	
	// UNIT TEST EXECUTION
	// Preparation - Execution - Assertion
	
    @Autowired
    private EntityManager entityManager;
    
    @Test
	void getCustomersTest() {
		// Preparation
		//new DAO - autowired
		//add customers
    	CustomerDAOImpl custDAO = new CustomerDAOImpl(entityManager);
    	
        entityManager.persist(new Customer("Filip", "", ""));
        entityManager.persist(new Customer("Karel", "", ""));
        entityManager.persist(new Customer("Petr", "", ""));


		
		// Execution
		//DAO.getCustomers - vrati list
        int size = custDAO.getCustomers().size();
		
		// Assertion
		//Assert
		assertEquals(3, size);		
		assertEquals("Karel", custDAO.getCustomer(2).getFirstName());
	}
	
	
}
