package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ProductsController implements Initializable{

	@FXML
	private Label lbllist;
	@FXML
	private ComboBox<String> cmblist;

	ObservableList<String> list;
	public int selectedIndexInListOfProducts;
	ClientConsole c = new ClientConsole();

	JOptionPane frame;
	Alert alert;
	
	public void setProductsComboBox() throws InterruptedException
	{
			ArrayList<String> al = new ArrayList<String>();	
			for(Product p:ClientConsole.Products)
			{
				if (p.getpName() != null)
					al.add(p.getpName());
			
			}
		
		list = FXCollections.observableArrayList(al);
		cmblist.setItems(list);
	}
	
	
	public void back(ActionEvent event) throws Exception {
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

	public  void updateIndex() throws Exception{
		selectedIndexInListOfProducts = (int)this.cmblist.getSelectionModel().getSelectedIndex();
		
	}
	public int getIndex() {
		return selectedIndexInListOfProducts;
	}
	
	 public void ProductInfo(ActionEvent event) throws Exception {
			
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/ProductDetails.fxml").openStream());
			ProductListController ProductFormController = loader.getController();	
			updateIndex();
			if(getIndex() < 0)
			{
				alert = new Alert(AlertType.WARNING, "Chose At least One Product");
				alert.setHeaderText("No Product Selected");
				alert.setTitle("Warning");
				alert.show();
			}
			else{
				
				((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
				ProductFormController.loadProduct(ClientConsole.Products.get(getIndex()));	
				Scene scene = new Scene(root);			
				scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
				primaryStage.setScene(scene);		
				primaryStage.setTitle("Product Details");
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
		}

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	
		try {
			setProductsComboBox();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
  }
public ComboBox<String> getComboBox()
 {
	return this.cmblist;
 }

}
