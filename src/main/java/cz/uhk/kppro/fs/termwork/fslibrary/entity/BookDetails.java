package cz.uhk.kppro.fs.termwork.fslibrary.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="book_details")
public class BookDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="year")
	private Integer year;
	
	@Column(name="isbn")
	private String isbn;
	
	@OneToMany(mappedBy="bookDetails", cascade= 
		{CascadeType.DETACH, CascadeType.MERGE,
		 CascadeType.PERSIST, CascadeType.REFRESH})
	private List<PhysicalCopy> physicalBooks;

	public BookDetails() {
	}	

	public BookDetails(String name, String description, Integer year, String isbn) {
		this.name = name;
		this.description = description;
		this.year = year;
		this.isbn = isbn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public void addPhysicalCopies(PhysicalCopy pc) {
		if (physicalBooks==null) {
			physicalBooks = new ArrayList<PhysicalCopy>();
		}
	
		physicalBooks.add(pc);
	}

	@Override
	public String toString() {
		return "BookDetails [id=" + id + ", name=" + name + ", description=" + description + ", year=" + year
				+ ", isbn=" + isbn + ", physicalBooks=" + physicalBooks + "]";
	}
	
	
	
}
