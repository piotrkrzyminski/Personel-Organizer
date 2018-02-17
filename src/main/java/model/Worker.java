package model;

import java.sql.Date;

public class Worker {

	private String PESEL;
	private String firstName;
	private String secondName;
	private String surname;
	private String email;
	private Date birthDate;
	private int salary;
	
	public Worker(String PESEL, String firstName, String secondName, String surname, String email, Date birthDate, int salary) {
		this.PESEL = PESEL;
		this.firstName = firstName;
		this.secondName = secondName;
		this.surname = surname;
		this.email = email;
		this.birthDate = birthDate;
		this.salary = salary;
	}
	
	public String getPESEL() {
		return PESEL;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSecondName() {
		return secondName;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public int getSalary() {
		return salary;
	}
	
}
