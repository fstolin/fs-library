package cz.uhk.kppro.fs.termwork.fslibrary.dao;

import java.util.List;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;
import cz.uhk.kppro.fs.termwork.fslibrary.entity.PhysicalCopy;

// DAO interface for database communication
public interface BookDAO {
	
	// Book copies
	
	public List<PhysicalCopy> getAllBookCopies();
	
	public void addBookCopies(PhysicalCopy book);

	public PhysicalCopy getBookCopyById(int id);

	public void deleteBookCopyById(int id);
	
	// Book details - aka book publication
	
	public List<BookDetails> getAllBookDetails();
	
	public void addBookDetails(BookDetails bd);
	
	public BookDetails getBookDetailsById(int id);
	
	public void deleteBookDetailsById(int id);

}
