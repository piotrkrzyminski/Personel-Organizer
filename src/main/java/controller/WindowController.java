package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class WindowController {
	
	@FXML
	private Label connectionLabel;
	@FXML
	public BorderPane window;
	
	public void setConnectionInfoText(boolean state) {
		if(state)
			connectionLabel.setText("connected");
		else
			connectionLabel.setText("not connected");
	}
	
	public void displayAddWorker() {
		window.setCenter(getParent("layout/addWorkerLayout.fxml"));
	}
	
	public void displayListWorker() {
		window.setCenter(getParent("layout/listLayout.fxml"));
	}
	
	public void displayEditWorker() {
		window.setCenter(getParent("layout/editWorkerLayout.fxml"));
	}
	
	public Parent getParent(String fxmlFileName) {
		Parent root = null;
		FXMLLoader loader;
		
		try {
			loader =  new FXMLLoader(getClass().getClassLoader().getResource(fxmlFileName));
			root = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return root;
	}
}
