package cz.uhk.kppro.fs.termwork.fslibrary.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="registration_date")
	private String registrationDate;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade= 
		{CascadeType.DETACH, CascadeType.MERGE,
		 CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="book_customer",
			joinColumns = @JoinColumn(name="customer_id"),
			inverseJoinColumns = @JoinColumn(name="book_id")
			)
	private List<PhysicalCopy> borrowedCopies;
	
	public List<PhysicalCopy> getBorrowedCopies() {
		return borrowedCopies;
	}


	public void setBorrowedCopies(List<PhysicalCopy> borrowedCopies) {
		this.borrowedCopies = borrowedCopies;
	}


	public Customer() {
		
	}	
	

	public Customer(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}


	@Override
	public String toString() {
		return "CustomerEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", date=" + registrationDate + "]";
	}	
	
	public void setOriginalRegistrationDate() {
		Date currentDate = new java.util.Date();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		this.registrationDate =  dateFormat.format(currentDate);
	}
	
	public void borrowPhysicalCopy(PhysicalCopy copy) {
		if (borrowedCopies == null) {
			borrowedCopies = new ArrayList<>();
		}
		
		borrowedCopies.add(copy);
	}
	
	public void returnPhysicalCopy(PhysicalCopy copy) {
		if (borrowedCopies == null) {
			borrowedCopies = new ArrayList<>();
		}
		
		borrowedCopies.remove(copy);
	}
	
}
