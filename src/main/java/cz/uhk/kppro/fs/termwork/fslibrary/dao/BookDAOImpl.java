package cz.uhk.kppro.fs.termwork.fslibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Repository
public class BookDAOImpl implements BookDAO {

	private EntityManager entityManager;
	
	// inject entity manager
	@Autowired
	public BookDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	// get all customers
	@Override
	public List<PhysicalCopy> getAllBookCopies() {
		TypedQuery<PhysicalCopy> theQuery = entityManager.createQuery("from PhysicalCopy order by bookDetails", PhysicalCopy.class);
		return theQuery.getResultList();
	}
	
	// add or update a customer
	@Override
	public void addBookCopies(PhysicalCopy book) {
		entityManager.merge(book);
	}

	// get customer by id
	@Override
	public PhysicalCopy getBookCopyById(int id) {
		return entityManager.find(PhysicalCopy.class, id);
	}
	
	// delete customer by id
	@Override
	public void deleteBookCopyById(int id) {
		Query theQuery = entityManager.createQuery("delete from PhysicalCopy where id =:theID");
		theQuery.setParameter("theID", id);
		theQuery.executeUpdate();
	}

	@Override
	public List<BookDetails> getAllBookDetails() {
		TypedQuery<BookDetails> theQuery = entityManager.createQuery("from BookDetails order by name", BookDetails.class);
		return theQuery.getResultList();
	}

	@Override
	public void addBookDetails(BookDetails bd) {
		entityManager.merge(bd);		
	}

	@Override
	public BookDetails getBookDetailsById(int id) {
		return entityManager.find(BookDetails.class, id);
	}

	@Override
	public void deleteBookDetailsById(int id) {
		Query theQuery = entityManager.createQuery("delete from BookDetails where id =:theID");
		theQuery.setParameter("theID", id);
		theQuery.executeUpdate();		
	}

	@Override
	public List<PhysicalCopy> getAllBorrowedBookCopies() {
		TypedQuery<PhysicalCopy> theQuery = entityManager.createQuery("from PhysicalCopy where borrowed = 1 order by bookDetails", PhysicalCopy.class);
		return theQuery.getResultList();
	}

}
