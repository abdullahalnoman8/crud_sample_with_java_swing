package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;
import GUI.MyFormEvent;

public class Controller {
	Database db = new Database();

	public List<Person> getPeople() {
		return db.getPeople();
	}

	public void removePerson(int index){
		db.removePerson(index);
	}
	
	public void addPerson(MyFormEvent event) {
		String name = event.getName();
		String occupation = event.getOccupation();
		int ageCatId = event.getAgeCategory();
		String empCat = event.getEmployeeCategory();
		boolean isBD = event.isBdCitizen();
		String taxID = event.getTaxId();
		String gender = event.getGender();

		// Formation of AgeCategory

		AgeCategory ageCategory = null;
		switch (ageCatId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		// For default we added the above null
		/*
		 * default: break;
		 */
		}

		EmploymentCategory empCategory;
		if (empCat.toLowerCase().equals("employed")) {
			empCategory = EmploymentCategory.employed;
		} else if (empCat.toLowerCase().equals("self-employed")) {
			empCategory = EmploymentCategory.selfEmployed;
		} else if (empCat.toLowerCase().equals("unemployed")) {
			empCategory = EmploymentCategory.unemployed;
		} else {
			empCategory = EmploymentCategory.other;
			System.err.println(empCat);
		}

		Gender genderCat;
		if (gender.equals("male")) {
			genderCat = Gender.male;
		} else {
			genderCat = Gender.female;
		}

		Person person = new Person(name, occupation, ageCategory, empCategory,
				taxID, isBD, genderCat);
		db.addPerson(person);
	}

	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
  /*
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
   */
	public void loadFromFile(File selectedFile) throws IOException {
       db.loadFromFile(selectedFile);
	}
}
