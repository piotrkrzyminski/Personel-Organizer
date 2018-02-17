package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Worker;

public class WorkerDAO {

	private static JDBCConnection con = JDBCConnection.getIntance();
	private static PreparedStatement prepStatement;
	private static Statement statement = con.getStatement();
	private static Connection connection = con.getConnection();
	
	public static void insertWorker(Worker worker) throws SQLException {
		String insert = "INSERT INTO " + JDBCConnection.TABLE_NAME + " VALUES(?,?,?,?,?,?,?);";
		
		prepStatement = connection.prepareStatement(insert);
		
		prepStatement.setString(1, worker.getPESEL());
		prepStatement.setString(2, worker.getFirstName());
		prepStatement.setString(3, worker.getSecondName());
		prepStatement.setString(4, worker.getSurname());
		prepStatement.setString(5, worker.getEmail());
		prepStatement.setDate(6, worker.getBirthDate());
		prepStatement.setInt(7, worker.getSalary());
		
		prepStatement.executeUpdate();
	}
	
	public static void updateWorker(Worker worker) throws SQLException {
		String sql = "UPDATE " + JDBCConnection.TABLE_NAME + " SET PESEL=?, first_name=?, secondName=?, surname=?, email=?, birth_date=?, salary=?;";
		
		prepStatement = connection.prepareStatement(sql);
		
		prepStatement.setString(1, worker.getPESEL());
		prepStatement.setString(2, worker.getFirstName());
		prepStatement.setString(3, worker.getSecondName());
		prepStatement.setString(4, worker.getSurname());
		prepStatement.setString(5, worker.getEmail());
		prepStatement.setDate(6, worker.getBirthDate());
		prepStatement.setInt(7, worker.getSalary());
		
		prepStatement.executeUpdate();
	}
	
	public static ObservableList<Worker> getWorkersList(String condition) throws SQLException {
		ObservableList<Worker> list = FXCollections.observableArrayList();
		
		String sql;
		if(condition == null || condition.equals(""))
			sql = "SELECT * FROM " + JDBCConnection.TABLE_NAME;
		else
			sql = "SELECT * FROM " + JDBCConnection.TABLE_NAME + " WHERE " + condition;
		
		ResultSet resultSet = statement.executeQuery(sql);
		
		String PESEL, firstName, secondName, surname, email;
		Date birthDate;
		int salary;
		
		while(resultSet.next()) {
			PESEL = resultSet.getString("PESEL");
			firstName = resultSet.getString("first_name");
			secondName = resultSet.getString("second_name");
			surname = resultSet.getString("surname");
			email = resultSet.getString("email");
			birthDate = resultSet.getDate("birth_date");
			salary = resultSet.getInt("salary");
			
			list.add(new Worker(PESEL, firstName, secondName, surname, email, birthDate, salary));
		}
		
		return list;
	}
	
	public static void deleteWorker(String PESEL) throws SQLException {
		String sql = "DELETE  FROM " + JDBCConnection.TABLE_NAME + " WHERE PESEL=" + PESEL + ";";

		prepStatement = connection.prepareStatement(sql);
		
		prepStatement.executeUpdate();
	}
}
