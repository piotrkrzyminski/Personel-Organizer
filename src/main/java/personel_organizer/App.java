package personel_organizer;

import java.sql.SQLException;

import controller.WindowController;
import database.JDBCConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	
	private static JDBCConnection dbConnection;
	private static WindowController controller;
	private static BorderPane borderPane;
	
	public static void main(String[] args) {
		performDBConnection();
		launch(args);
	}
	
	private static void performDBConnection() {
		dbConnection = JDBCConnection.getIntance();
		
		dbConnection.connect();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader windowLayoutLoader = new FXMLLoader(getClass().getClassLoader().getResource("layout/windowLayout.fxml"));
		borderPane = (BorderPane) windowLayoutLoader.load();
		controller = windowLayoutLoader.getController();
		
		Scene scene = new Scene(borderPane);
		
		stage.setScene(scene);
		stage.show();
		controller.setConnectionInfoText(dbConnection.isConnected());
		controller.displayListWorker();
	}	
	
	@Override
	public void stop() throws SQLException {
		dbConnection.close();
	}
	
	public static WindowController getController() {
		return controller;
	}
}
