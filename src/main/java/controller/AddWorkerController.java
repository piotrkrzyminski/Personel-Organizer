package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import database.WorkerDAO;
import database.WorkerDataParser;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Worker;

public class AddWorkerController {

	@FXML
	public TextField peselField;
	@FXML
	public TextField firstNameField;
	@FXML
	public TextField secondNameField;
	@FXML
	public TextField surnameField;
	@FXML
	public TextField emailField;
	@FXML
	public DatePicker birthDatePicker;
	@FXML
	public TextField salaryField;
	@FXML
	public Label addWorkerInfoLabel;
	
	private WorkerDataParser parser;
	
	public void addWorker() {
		Worker worker = createWorker();
		
		if(isWorkerDataProper(worker)){
			try {
				WorkerDAO.insertWorker(worker);
				addWorkerInfoLabel.setText("New worker was added to database");
			} catch (SQLException e) {
				addWorkerInfoLabel.setText("Error. Worker was not added to database");
				e.printStackTrace();
			} finally {
				clearData();
			}
		}
	}
	
	public void clearData() {
		peselField.clear();
		firstNameField.clear();
		secondNameField.clear();
		surnameField.clear();
		emailField.clear();
		birthDatePicker.getEditor().clear();
		birthDatePicker.setValue(null);
		salaryField.clear();
	}
	
	private Worker createWorker() {
		String PESEL = peselField.getText().trim();
		String firstName = firstNameField.getText().trim();
		String secondName = null;
		
		if(!secondNameField.getText().isEmpty())
			secondName = secondNameField.getText().trim();
		else
			secondName = "";
		
		String surname = surnameField.getText().trim();
		String email = emailField.getText().trim();
		LocalDate birthDate = birthDatePicker.getValue();
		int salary = Integer.valueOf(salaryField.getText());
		
		return new Worker(PESEL, firstName, secondName, surname, email, Date.valueOf(birthDate), salary);
	}
	
	private boolean isWorkerDataProper(Worker worker) {
		parser = new WorkerDataParser(worker);
		
		if(!parser.isPeselValid()) {
			addWorkerInfoLabel.setText("PESEL is not valid!");
			return false;
		}
			
		
		if(!parser.isFirstNameValid()) {
			addWorkerInfoLabel.setText("First name is not valid!");
			return false;
		}
		
		if(!parser.isSurnameValid()) {
			addWorkerInfoLabel.setText("Surname is not valid!");
			return false;
		}
		
		if(!parser.isSalaryValid()) {
			addWorkerInfoLabel.setText("Salary can not be nagative!");
			return false;
		}
		
		return true;
	}
}
