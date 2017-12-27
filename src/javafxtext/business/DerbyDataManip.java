package javafxtext.business;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DerbyDataManip {
	
	private boolean isUpdate = false;
	
     public DerbyDataManip(){
    	 Connection con = getConnection();
    	 try {
    		
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "PERSONTABLE", null);
			if(tables.next()){
				
			}
			else{
				 try {
						Statement stmt = con.createStatement();
						stmt.executeUpdate("CREATE TABLE PersonTable(ID INTEGER not null primary key"+
                          " GENERATED ALWAYS AS IDENTITY "+
                           "(START WITH 1, INCREMENT BY 1),FirstName VARCHAR(20),"
								+ "LastName VARCHAR(20))");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	 try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    	 
     }
	private Connection getConnection(){
		String dbUrl = "jdbc:derby:c/MDB;create = true";
		String userName = "name";
		String pass = "pass";
		try{
			Connection con = DriverManager.getConnection(dbUrl,userName,pass);
			return con;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}

	}
	public ObservableList<Person> getFromTable(){
		ObservableList<Person> people = FXCollections.observableArrayList();
		String sql = "SELECT * FROM PersonTable";
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery())
		{
			while (rs.next()){
				int id = rs.getInt("ID");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				
				Person p = new Person(id,firstName,lastName);
				people.add(p);
			}
			return people;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
	}
	public void addDerbyDatabase(String firstName, String lastName){
	 String sql = "INSERT INTO PersonTable (firstName,lastName) "
	 		+ "VALUES(?,?)";
	 try(Connection con = getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);){
		 ps.setString(1, firstName);
		 ps.setString(2, lastName);		
		 ps.executeUpdate();
		 ps.close();
		 con.close();
		 
	 }catch(SQLException e){
		 e.printStackTrace();
	 }
	}
	public void deleteFromTable(Person person){
		String sql = "DELETE FROM PersonTable WHERE ID = ?";
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, person.getID());
			ps.executeUpdate();
			ps.close();
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void updateTable(Person person){
		String sql = "UPDATE PersonTable "
				+ "SET firstName = ?, lastName = ?  WHERE ID = ?";
		
		try(Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, person.getFirstName());
			System.out.println("first name = "+person.getFirstName());
			ps.setString(2, person.getLastName());
			System.out.println("last name = "+person.getLastName());
			ps.setInt(3, person.getID());
			System.out.println("person id = "+person.getID());
			ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}
	}
	public boolean getIsUpdate(){
		return isUpdate;
	}
	public void setIsUpdate(boolean isUpdate){
		this.isUpdate = isUpdate;
	}
	
	
}
