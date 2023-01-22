package cz.uhk.kppro.fs.termwork.fslibrary.dto;

import java.util.List;

import cz.uhk.kppro.fs.termwork.fslibrary.entity.BookDetails;

public class BookDTO {
	
	private int detailId;
	
	private int amountOfCopies;
	
	List<BookDetails> details;
	
	

	public BookDTO() {
	}

	public BookDTO(int detailId, int amountOfCopies, List<BookDetails> details) {
		this.detailId = detailId;
		this.amountOfCopies = amountOfCopies;
		this.details = details;
	}

	public List<BookDetails> getDetails() {
		return details;
	}

	public void setDetails(List<BookDetails> details) {
		this.details = details;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getAmountOfCopies() {
		return amountOfCopies;
	}

	public void setAmountOfCopies(int amountOfCopies) {
		this.amountOfCopies = amountOfCopies;
	}
	
	
}
