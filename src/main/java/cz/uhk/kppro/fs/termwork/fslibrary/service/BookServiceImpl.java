package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.BookDAO;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.Customer;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;

@Service
public class BookServiceImpl implements BookService {

	private BookDAO bookDAO;
	
	@Autowired
	public BookServiceImpl(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@Override
	@Transactional
	public List<PhysicalCopy> getAllBookCopies() {
		return bookDAO.getAllBookCopies();
	}

	@Override
	@Transactional
	public void saveBookCopy(PhysicalCopy book) {
		bookDAO.addBookCopies(book);
	}

	@Override
	@Transactional
	public PhysicalCopy getBookCopy(int id) {
		return bookDAO.getBookCopyById(id);
	}

	@Override
	@Transactional
	public void deleteBookCopy(int id) {
		bookDAO.deleteBookCopyById(id);
	}
	
	@Override
	@Transactional
	public List<BookDetails> getAllBookDetails() {
		return bookDAO.getAllBookDetails();
	}

	@Override
	@Transactional
	public void saveBookDetials(BookDetails bd) {
		bookDAO.addBookDetails(bd);		
	}

	@Override
	@Transactional
	public BookDetails getBookDetailsById(int id) {
		return bookDAO.getBookDetailsById(id);
	}

	@Override
	@Transactional
	public void deleteBookDetails(int id) {
		bookDAO.deleteBookDetailsById(id);		
	}
	
	// Add multiple copies of book
	@Override
	@Transactional
	public void saveBookCopy(PhysicalCopy book, int amount) {
		if (amount > 100 || amount < 1) {
			throw new RuntimeException("Invalid amount, max 100, min 1");
		} else {
			for (int i = 0; i < amount; i++) {
				bookDAO.addBookCopies(book);
			}	
		}	
	}
	
	// Get Publications available to borrow
	@Override
	@Transactional
	public List<BookDetails> getAvailablePublications(){
		List<BookDetails> pList = new ArrayList<>();
		List<PhysicalCopy> copyList = getAllBookCopies();
		
		for (PhysicalCopy copy : copyList){
			BookDetails bDetails = copy.getBookDetails();
			// If the book is already in the available books, or the physical copy is borrowed, skip to next
			if (pList.contains(bDetails) || copy.getBorrowed() == 1){
				continue;
			} else {
				pList.add(bDetails);
			}
		}
		System.out.println(pList);
		return pList;
	}
	
	// Set physical copy as borrowed by Book Details id. There shouldn't be a case, where a single physical copy wouldn't be available
	// Returns physical copy book ID
	@Override
	@Transactional
	public int setBorrowedByDetailsId(int id) {
		BookDetails bDetails = bookDAO.getBookDetailsById(id);
		List<PhysicalCopy> copies = bDetails.getPhysicalBooks();
		// loop through copies and select the first available
		for (PhysicalCopy c : copies) {
			if (c.getBorrowed() == 0) {
				c.setBorrowed(1);
				bookDAO.addBookCopies(c);
				return c.getId();
			}
		}
		return 0;
	}

	//Return list of physical copies for a publication, that are available to be borrowed 
	@Override
	@Transactional
	public List<PhysicalCopy> getAvailablePhysicalCopies(int id) {
		BookDetails book = bookDAO.getBookDetailsById(id);
		List<PhysicalCopy> copies = book.getPhysicalBooks();
		List<PhysicalCopy> availableCopies = new ArrayList<>();
		
		// remove borrowed copies
		for (PhysicalCopy copy : copies) {
			if(copy.getBorrowed()==0) {
				availableCopies.add(copy);
			}
		}

		return availableCopies;
	}
	
	// Temporary implementation, Many to Many relation is scalable for history etc. Currently not implemented
	@Override
	@Transactional
	public int getFirstBorrower(int id) {
		Customer cust = bookDAO.getBookCopyById(id).getBorrowers().get(0);
		return cust.getId();
	}

	@Override
	public List<PhysicalCopy> getAllBorrowedPhysicalCopies() {
		return bookDAO.getAllBorrowedBookCopies();
	}

}
