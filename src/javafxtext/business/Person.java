package javafxtext.business;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {
	private final SimpleStringProperty firstName ;
	private final SimpleStringProperty lastName;
	private final SimpleIntegerProperty ID;
	
	public Person(int ID,String firstName, String lastName){
		this.ID = new SimpleIntegerProperty(ID);
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		
	}
	public void setFirstName(String firstName){
		this.firstName.set(firstName);
	}
	public void setLastName(String lastName){
		this.lastName.set(lastName);
	}
	public String getFirstName(){
		return firstName.get();
	}
	public String getLastName(){
		return lastName.get();
	}
	public void setID(int ID){
		this.ID.set(ID);
	}
	public int getID(){
		return ID.get();
	}
}
