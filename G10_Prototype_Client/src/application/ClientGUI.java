package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientGUI extends Application {
	
	JOptionPane frame;
	public static String ip;
	public static void main(String args[]) {
		try{
			ip=args[0];
		}
		 catch(Exception e)
	    {
	     
	    }
		launch(args);
	}
@Override
public void start (Stage primaryStage) throws IOException {
		
		Parent root;

		root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));

		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setTitle("Zr-Le Login System");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			public void handle(WindowEvent we) {
				
				JOptionPane.showMessageDialog(frame, "Goodbye , See ya Next Time :) ");
				System.exit(0);
			}
		});
	}
}
