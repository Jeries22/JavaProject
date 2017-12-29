package application;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ProductListController {

	
	private Product p;
	@FXML
	private Label lblPID;
	@FXML
	private Label lblpName;
	@FXML
	private Label lblPtype;
	@FXML
	private TextField txtPID;
	@FXML
	private TextField txtpName;
	@FXML
	private TextField txtPtype;
	@FXML
	private Button btnsave;
	@FXML 
	private byte[] image;
	ObservableList<String> list;
	ClientConsole c = new ClientConsole();
	ArrayList<Product> tempList;
	JOptionPane frame;
	public static Alert alert;
	 public void loadProduct(Product P1){
			this.p=P1;
			txtpName.setText(p.getpName());
			txtPID.setText(p.getId());	
			txtPtype.setText(p.getPtype());
			
		}
	
	public void BackToMenu(ActionEvent event) throws IOException
	{
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
	
	public void PList(ActionEvent event) throws IOException
	{
		((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
		Stage primaryStage = new Stage();
		Parent root1;

		root1 = FXMLLoader.load(getClass().getResource("/application/ProductsList.fxml"));

		
		Scene scene = new Scene(root1);
		scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
		primaryStage.setTitle("List");
		primaryStage.setScene(scene);
		
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
		{
			public void handle(WindowEvent we) {
				
				JOptionPane.showMessageDialog(frame, "You disconnected from the server");
				ClientConsole.client.quit();
				System.exit(0);
			}
		});
	}
	 public void getSAaveBtn(ActionEvent event) throws Exception 
		{
		 
		 /*Update changes to GUI*/
		 int index;
		 Product newP = new Product(txtPID.getText(),
				 					txtpName.getText(),
				 					txtPtype.getText());
		 	
		 	index = ClientConsole.Products.indexOf(p);	
			ClientConsole.Products.set(index, newP);
			this.p = newP;
			
			
		/*Update changes to Database*/	
		
		handleSaveToDB(newP, index);
		Alert alert = new Alert(AlertType.INFORMATION, "Product Was Update Sucssesfully", ButtonType.OK);
		alert.setTitle("Update");
		alert.show();
	
	}
	 
	 public void handleSaveToDB(Product newP, int index) {
		 try{
				ClientConsole.client.sendToServer(index);	//send the selected index
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			try{
				ClientConsole.client.sendToServer(newP);		//send new product details to server to save it in DB
			}catch(IOException e) {
				e.printStackTrace();
			}
	 }
}
