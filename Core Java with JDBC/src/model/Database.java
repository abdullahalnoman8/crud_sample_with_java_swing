package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Database {
	private List<Person> people;
	private Connection con;

	public Database() {
		people = new LinkedList<Person>();
	}

	public void connect() throws Exception {
		if (con != null) {
			return;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}

		String url = "jdbc:mysql://localhost:3306/swingtest";
		con = (Connection) DriverManager.getConnection(url, "root", "");
		System.out.println("Connected "+con.toString());

	}

	public void disconnect() {
		if (con != null) {
            try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}
	}

	public void save() throws SQLException{
		
		String checkSql = " Select count(*) as count from people where id = ?";
		
		PreparedStatement checkStmt =  (PreparedStatement) con.prepareStatement(checkSql);
		
		String insertSql = "insert into people (ID,Name, Occupation,AgeCategory,Employment,Tax_ID,BD_Cityzen,Gender) values(?,?,?,?,?,?,?,?)";
		
		PreparedStatement insertStatement = (PreparedStatement) con.prepareStatement(insertSql);
		
		
		for (Person person: people) {
			int id = person.getP_id();
			String name= person.getName();
			String occupation = person.getOccupation();
			AgeCategory age = person.getAgeCategory();
			EmploymentCategory emp = person.getEmpCat();
			String tax = person.getTaxId();
			boolean isBd = person.isBdCitizen();
			Gender gender = person.getGender();
			
			checkStmt.setInt(1, id);
			
			ResultSet checkResult = checkStmt.executeQuery();
			checkResult.next();
			
			int count = checkResult.getInt(1);
			
			if(count ==0){
				System.out.println("Inserting Person with ID " +id);
				
				int col =1;
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, occupation);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, emp.name());
				insertStatement.setString(col++, tax);
				insertStatement.setBoolean(col++, isBd);
				insertStatement.setString(col++, gender.name());
				
				insertStatement.executeUpdate();
				
			}else{
				System.out.println("Updating person with ID " + id);
			}
			
			//System.out.println("Count for person with ID: "+ id + " is " +count );
			
		}
		insertStatement.close();
		checkStmt.close();
	}
	
	public void addPerson(Person person) {
		people.add(person);
	}

	public void removePerson(int index) {
		people.remove(index);
	}

	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);
	}

	// For Save File
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		Person[] persons = people.toArray(new Person[people.size()]);

		oos.writeObject(persons);

		oos.close();
	}

	// load From the file
	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);

		try {
			Person[] persons = (Person[]) ois.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ois.close();
	}
}
