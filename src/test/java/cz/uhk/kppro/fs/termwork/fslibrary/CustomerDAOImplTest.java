package cz.uhk.kppro.fs.termwork.fslibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.BookDAOImpl;
import cz.uhk.kppro.fs.termwork.fslibrary.dao.CustomerDAOImpl;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import jakarta.persistence.EntityManager;

@RunWith(SpringRunner.class)
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
    	BookDAOImpl bookDAO = new BookDAOImpl(entityManager);
    	
        entityManager.persist(new BookDetails("karel", "", 20, ""));
        entityManager.persist(new BookDetails("karel2", "", 20, ""));


		
		// Execution
		//DAO.getCustomers - vrati list
        int size = bookDAO.getAllBookDetails().size();
        System.out.println(size);
		
		// Assertion
		//Assert
		assertEquals(2, size);		
	}
    
    @Test
	void getCustomers2Test() {
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
