package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.WorkerDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Worker;

public class WorkersListController implements Initializable {

	@FXML
	private TableView<Worker> workersTableView;
	@FXML
	private TableColumn<Worker, String> peselColumn;
	@FXML
	private TableColumn<Worker, String> firstNameColumn;
	@FXML
	private TableColumn<Worker, String> secondNameColumn;
	@FXML
	private TableColumn<Worker, String> surnameColumn;
	@FXML
	private TableColumn<Worker, String> emailColumn;
	@FXML
	private TableColumn<Worker, String> birthDateColumn;
	@FXML
	private TableColumn<Worker, Integer> salaryColumn;
	@FXML
	private Label operationInfoLabel;
	@FXML
	private TextField conditionField;

	public void initialize(URL arg0, ResourceBundle arg1) {
		updateTable();
	}
	
	public void updateTable() {
		String condition = conditionField.getText().trim();
		
		try {
			ObservableList<Worker> list = WorkerDAO.getWorkersList(condition);
			
			workersTableView.setItems(list);
			peselColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("PESEL"));
			firstNameColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("firstName"));
			secondNameColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("secondName"));
			surnameColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("surname"));	
			emailColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("email"));	
			birthDateColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("birthDate"));
			salaryColumn.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("salary"));
			
		} catch (SQLException e) {
			operationInfoLabel.setText("Failed to update table");
			e.printStackTrace();
		}
	}
	
	private Worker getWorkerFromTable() {
		Worker selectedWorker = workersTableView.getSelectionModel().getSelectedItem();
		
		if(selectedWorker == null) {
			operationInfoLabel.setText("Select row from table before delete");
			return null;
		}
		
		return selectedWorker;
	}
	
	public void onClickDelete() {
		Worker selectedWorker = getWorkerFromTable();
		
		if(selectedWorker == null)
			return;
		
		String pesel = selectedWorker.getPESEL();
		
		try {
			WorkerDAO.deleteWorker(pesel);
			operationInfoLabel.setText(selectedWorker.getFirstName() + " " + selectedWorker.getSurname() + " was deleted from database");
			updateTable();
		} catch (SQLException e) {
			operationInfoLabel.setText("Error. Row was not deleted");
			e.printStackTrace();
		}	
	}
	
	public void inClickFind() {
		updateTable();
	}
	
	public void clearCondition() {
		conditionField.clear();
		updateTable();
	}
}
