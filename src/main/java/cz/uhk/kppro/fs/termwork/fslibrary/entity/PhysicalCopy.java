package cz.uhk.kppro.fs.termwork.fslibrary.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="physical_copies")
public class PhysicalCopy {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="borrowed")
	private int borrowed;
	
	@ManyToOne(cascade= 
		{CascadeType.DETACH, CascadeType.MERGE,
		 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="book_detail_id")
	private BookDetails bookDetails;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= 
		{CascadeType.DETACH, CascadeType.MERGE,
		 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="book_customer",
			joinColumns = @JoinColumn(name="book_id"),
			inverseJoinColumns = @JoinColumn(name="customer_id")
			)
	private List<Customer> borrowers;
	
	public List<Customer> getBorrowers() {
		return borrowers;
	}

	public void setBorrowers(List<Customer> borrowers) {
		this.borrowers = borrowers;
	}

	public PhysicalCopy() {	
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public int getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(int borrowed) {
		this.borrowed = borrowed;
	}

	public BookDetails getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(BookDetails bookDetails) {
		this.bookDetails = bookDetails;
	}

	@Override
	public String toString() {
		return "PhysicalCopy [id=" + id + ", bookDetailId=" + ", borrowed=" + borrowed + "]";
	}	
	
	public String getFirstBorrower() {
		return borrowers.get(0).getFirstName();
	}
	
}
