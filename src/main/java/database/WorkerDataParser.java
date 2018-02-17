package database;

import model.Worker;

public class WorkerDataParser {
	
	private Worker worker;
	
	public WorkerDataParser(Worker worker) {
		this.worker = worker;
	}
	
	public boolean isPeselValid() {
		String pesel = worker.getPESEL();
		
		return (pesel.matches("\\d{11}"));
	}
	
	public boolean isFirstNameValid() {
		String firstName = worker.getFirstName();
		
		return (!firstName.equals("") || firstName != null);
	}
	
	public boolean isSurnameValid() {
		String surname = worker.getFirstName();
		
		return (!surname.equals("") || surname != null);
	}
	
	public boolean isEmailValid() {
		String email = worker.getFirstName();
		
		return (!email.equals("") || email != null);
	}
	
	public boolean isSalaryValid() {
		int salary = worker.getSalary();
		
		return (salary > 0);
	}
}
