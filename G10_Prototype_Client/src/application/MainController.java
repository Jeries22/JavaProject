package application;
	
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MainController {
	JOptionPane frame;

	public void ViewItemsList(ActionEvent event) throws Exception
	{
		
		Pane root;
		((Node) event.getSource()).getScene().getWindow().hide();
		Stage PrimaryStage = new Stage();
	
		 root = FXMLLoader.load(getClass().getResource("/application/ProductsList.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		PrimaryStage.setScene(scene);
		PrimaryStage.setTitle("ListOfItems");
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
	
	public void Logout(ActionEvent event)
	{
		JOptionPane.showMessageDialog(frame, "Goodbye , See ya Next Time :)");
		System.exit(0);
	}
	
}
