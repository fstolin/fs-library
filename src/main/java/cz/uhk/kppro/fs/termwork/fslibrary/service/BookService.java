package cz.uhk.kppro.fs.termwork.fslibrary.service;

import java.util.List;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;

// Service interface for business logic
public interface BookService {
	
	public List<PhysicalCopy> getAllBookCopies();

	public void saveBookCopy(PhysicalCopy book);
	
	public void saveBookCopy(PhysicalCopy book, int amount);
	
	public PhysicalCopy getBookCopy(int id);

	public void deleteBookCopy(int id);
	
	public List<BookDetails> getAllBookDetails();

	public void saveBookDetials(BookDetails bd);
	
	public BookDetails getBookDetailsById(int id);

	public void deleteBookDetails(int id);
	
}
