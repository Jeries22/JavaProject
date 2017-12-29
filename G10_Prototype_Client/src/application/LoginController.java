package application;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController {
	@FXML
	private TextField txtUsername;
	@FXML
	private TextField txtPassword;


	JOptionPane frame;
	
	public boolean CheckUserExits(String userID,String userPass)
	{
		boolean flag=false;
		String msg= new String("Check-"+userID+"-"+ userPass);
		try {
			ClientConsole.client.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public void connectNOW() {
	ClientConsole c = new ClientConsole();
		c.startconnection();
	}
	

	public void PerformLogin(ActionEvent event) throws IOException
	{
		if(CheckUserExits(txtUsername.getText(),txtPassword.getText()))
	//if(txtUsername.getText().equals("Jeries")&& txtPassword.getText().equals("159632") )
		{
			connectNOW();
			Parent root;
			
			((Node) event.getSource()).getScene().getWindow().hide();
			Stage PrimaryStage = new Stage();
			 root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			PrimaryStage.setScene(scene);
			PrimaryStage.setTitle("Zr-Le Main Menu");
			
			PrimaryStage.show();
			
			PrimaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
			{
				public void handle(WindowEvent we) {
					
					JOptionPane.showMessageDialog(frame, "You disconnected from the server");
					ClientConsole.client.quit();
					System.exit(0);
				}
			});
		}
		else
		{
			Alert alert = new Alert(AlertType.ERROR, "Invalid Username or Password.", ButtonType.CLOSE);
			alert.setTitle("Login failure");
			alert.setHeaderText("Re-try Again");
			alert.show();
		}
	}
	
	public void exitLogin() {
		JOptionPane.showMessageDialog(frame, "Goodbye , See ya Next Time :)");
		System.exit(0);
	}
	
}
