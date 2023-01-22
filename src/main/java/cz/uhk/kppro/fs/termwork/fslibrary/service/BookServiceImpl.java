package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.kppro.fs.termwork.fslibrary.dao.BookDAO;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
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



}
