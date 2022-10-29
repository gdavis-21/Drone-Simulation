package com.barishal;

import java.io.IOException;
import com.barishal.view.Singleton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Design And Implementation Pt. 1");
		
		initRootLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initRootLayout() {
		try {
			
			Singleton dashboardViewController = Singleton.getInstance();
			
			// Load RootLayout from FXML file.
			FXMLLoader loader = new FXMLLoader();
			
			loader.setController(dashboardViewController);
			
			loader.setLocation(MainApp.class.getResource("view/DashboardView.fxml"));
			rootLayout = (AnchorPane) loader.load();
			
			// Show scene containing rootLayout;
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
