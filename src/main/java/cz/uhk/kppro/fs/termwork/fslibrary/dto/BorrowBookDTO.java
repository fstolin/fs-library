package cz.uhk.kppro.fs.termwork.fslibrary.dto;

public class BorrowBookDTO {
	
	private int bookId;
	
	private int customerId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public BorrowBookDTO() {
	}	
	
}
