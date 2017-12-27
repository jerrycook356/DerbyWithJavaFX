package javafxtest.views;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxtext.business.AlertDialog;
import javafxtext.business.DerbyDataManip;
import javafxtext.business.Person;
import javafxtext.business.Validator;

public class TableViewController {
  @FXML private TableView<Person> tableView = new TableView<Person>();
  @FXML private TableColumn<Person,String> firstNameCol = new TableColumn<Person, String>();
  @FXML private TableColumn<Person,String> lastNameCol = new TableColumn<Person, String>();
  
  @FXML private Button deleteButton = new Button();
  @FXML private Button saveButton = new Button();
  @FXML private Button cancelButton = new Button();
  @FXML private Button UpdateButton = new Button();
  
  @FXML private TextField firstNameTextField = new TextField();
  @FXML private TextField lastNameTextField = new TextField();
  
  
  private ObservableList<Person>data = FXCollections.observableArrayList();
  
  DerbyDataManip dm = new DerbyDataManip();
  Validator val = new Validator();
  AlertDialog ad = new AlertDialog();
  
  public TableViewController(){
	 
  }
  @FXML
  public void initialize() {
	  
	  lastNameTextField.setDisable(true);
	  firstNameTextField.setDisable(true);
	  saveButton.setDisable(true);
	  cancelButton.setDisable(true);
	  
	  
      firstNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("FirstName"));
      lastNameCol.setCellValueFactory(new PropertyValueFactory<Person,String>("LastName"));
      
      
      refreshView();
     
	 
  }
  @FXML
  private void onDeleteButtonPressed(){
	  Person person = tableView.getSelectionModel().getSelectedItem();
	  data.remove(person);
	  dm.deleteFromTable(person);
	  tableView.getItems().setAll(data);
	 
  }
  @FXML 
  private void onAddButtonPressed(){
	 enableEntryControls();
  }
  @FXML
  private void onSaveButtonPressed(){
	 boolean isUpdate = dm.getIsUpdate();
	  
	
	  String firstName = firstNameTextField.getText();
	  String lastName = lastNameTextField.getText();
	  boolean isFirstName = val.isValid(firstName);
	  boolean isLastName = val.isValid(lastName);
	 if(isUpdate){		 
			 if(!isFirstName){
				   ad.alert("Invalid entry for first name");
				   firstNameTextField.requestFocus();
			   }
			   if(!isLastName){
				   ad.alert("Invalid entry for last name");
				   lastNameTextField.requestFocus();
			   }
			   if((isFirstName)&&(isLastName)){
				   updateSave();
					  dm.setIsUpdate(false);
					  disableEntryControls();			 
			   }
				 }
		 
	 else{
	  
	   if(!isFirstName){
		   ad.alert("Invalid entry for first name");
		   firstNameTextField.requestFocus();
	   }
	   if(!isLastName){
		   ad.alert("Invalid entry for last name");
		   lastNameTextField.requestFocus();
	   }
	   if((isFirstName)&&(isLastName)){
		   
	  dm.addDerbyDatabase(firstName,lastName);
	  disableEntryControls();
	   }
		 }
	 
	  
	
	 refreshView();
	 
  }
  @FXML
  private void onCancelButtonPressed(){
	  disableEntryControls();
  }
  @FXML
  private void onUpdateButtonPressed(){
	  dm.setIsUpdate(true);
	  try{
	  Person person = tableView.getSelectionModel().getSelectedItem();
      enableEntryControls();
	  
	  firstNameTextField.setText(person.getFirstName());
	  lastNameTextField.setText(person.getLastName());
	  }catch(NullPointerException e){
		  ad.alert("Nothing selected to update");
		  
	  }
  
	 

  }
  private void updateSave(){
	  Person person = tableView.getSelectionModel().getSelectedItem();
	  person.setFirstName(firstNameTextField.getText());
	  person.setLastName(lastNameTextField.getText());
	  dm.updateTable(person);
  }
  private void refreshView(){
	  data = dm.getFromTable();
	  tableView.getItems().setAll(data);
  }
  private void disableEntryControls(){
	  firstNameTextField.setText("");
	  lastNameTextField.setText("");
	  
	  firstNameTextField.setDisable(true);
	  lastNameTextField.setDisable(true);
	  saveButton.setDisable(true);
	  cancelButton.setDisable(true);
	 
  }
  private void enableEntryControls(){
	  firstNameTextField.setDisable(false);
	  lastNameTextField.setDisable(false);
	  saveButton.setDisable(false);
	  cancelButton.setDisable(false);
	  
  }
}
