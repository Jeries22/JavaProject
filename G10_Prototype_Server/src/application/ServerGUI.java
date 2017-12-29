package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class ServerGUI extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		ServerController sc = new ServerController();
		sc.start(primaryStage);
	}
}