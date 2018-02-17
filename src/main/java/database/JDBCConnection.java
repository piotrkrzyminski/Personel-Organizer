package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {
	
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String DB_NAME = "jdbc:sqlite:workers.db";
	public static final String TABLE_NAME = "personel";
	
	private boolean isConnected = false;
	private String selectedDBName = null;
	
	private static JDBCConnection con = null;
	private Connection connection = null;
	private Statement statement = null;
	
	private JDBCConnection() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCConnection getIntance() {
		if(con == null) {
			con = new JDBCConnection();
		}
		
		return con;
	}
	
	public void connect() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					connection = DriverManager.getConnection(DB_NAME);
					statement = connection.createStatement();
					isConnected = true;
					createTable();
				} catch (SQLException e) {
					isConnected = false;
					e.printStackTrace();
				}
			}
		});
		
		t.start();
		
	}
	
	private void createTable() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME + 
						"(PESEL INTEGER PRIMARY KEY," + 
						"first_name VARCHAR(60) NOT NULL," + 
						"second_name VARCHAR(60)," + 
						"surname VARCHAR(60) NOT NULL," + 
						"email VARCHAR(60) NOT NULL,"+
						"birth_date DATE NOT NULL," + 
						"salary INTEGER NOT NULL);";
				
				try {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		t.start();
		
	}
	
	public void close() throws SQLException {
		connection.close();
		isConnected = false;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public boolean isConnected() {
		return isConnected;
	}
	
	public String getSelectedDBName() {
		return this.selectedDBName;
	}
}
