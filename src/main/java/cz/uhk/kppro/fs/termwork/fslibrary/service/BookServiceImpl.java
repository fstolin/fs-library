package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<PhysicalCopy> getAllBookCopies() {
		return bookDAO.getAllBookCopies();
	}

	@Override
	public void saveBookCopy(PhysicalCopy book) {
		bookDAO.addBookCopies(book);
	}

	@Override
	public PhysicalCopy getBookCopy(int id) {
		return bookDAO.getBookCopyById(id);
	}

	@Override
	public void deleteBookCopy(int id) {
		bookDAO.deleteBookCopyById(id);
	}
	
	@Override
	public List<BookDetails> getAllBookDetails() {
		return bookDAO.getAllBookDetails();
	}

	@Override
	public void saveBookDetials(BookDetails bd) {
		bookDAO.addBookDetails(bd);		
	}

	@Override
	public BookDetails getBookDetailsById(int id) {
		return bookDAO.getBookDetailsById(id);
	}

	@Override
	public void deleteBookDetails(int id) {
		bookDAO.deleteBookDetailsById(id);		
	}



}
