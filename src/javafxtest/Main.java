package javafxtest;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafxtext.business.DerbyDataManip;

public class Main extends Application {
	DerbyDataManip dm = new DerbyDataManip();
    private Stage primaryStage;
    private BorderPane mainLayout;
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("test table view");
		
		showMainView();
		showTableView();
	}
    private void showMainView() throws IOException{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("views/MainLayout.fxml"));
    	mainLayout = loader.load();
    	Scene scene = new Scene(mainLayout);
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
    private void showTableView() throws IOException{
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(Main.class.getResource("views/TableView.fxml"));
    	BorderPane tableView = loader.load();
    	mainLayout.setCenter(tableView);
    }
	public static void main(String[] args) {
		launch(args);
	}
}
