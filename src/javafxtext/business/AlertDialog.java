package javafxtext.business;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertDialog {
	
	String message;

	public AlertDialog(){
		
	}
	public void alert(String message){
		this.message = message;
		
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("ERROR !!!!");
		alert.setHeaderText(null);
		alert.setContentText(message);
		
		alert.showAndWait();
	}

}
