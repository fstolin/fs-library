package cz.uhk.kppro.fs.termwork.fslibrary;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.CustomerDAOImpl;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerDAOImplTest {
	
	// UNIT TEST EXECUTION
	// Preparation - Execution - Assertion
	
 	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerDAOImpl customerDAO;

	
    @Test
	void getCustomersTest() {
		// Preparation
		//new DAO - autowired
		//add customers
    	
		entityManager.persist(new Customer("Filip", "", ""));
		entityManager.persist(new Customer("Petr", "", ""));
		entityManager.persist(new Customer("Karel", "", ""));
		
		// Execution
		//DAO.getCustomers - vrati list
		List<Customer> customers = customerDAO.getCustomers();
		
		// Assertion
		//Assert
		assertEquals(3, customers.size());
		
	}
	
	
}
