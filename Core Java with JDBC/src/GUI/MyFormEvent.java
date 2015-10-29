package GUI;

import java.util.EventObject;

public class MyFormEvent extends EventObject{

	private String name;
	private String occupation;
	private int AgeCategory;
	private String empCat;
	private String taxId;
	private boolean bdCitizen;
	private String gender;
	
	
	public MyFormEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public MyFormEvent(Object source,String name,String occupation, int ageCat,String empCat) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.AgeCategory = ageCat;
		this.empCat = empCat;
	}
	
	public MyFormEvent(Object source,String name,String occupation, int ageCat,String empCat,String taxId,boolean bdCitizen,String gender) {
		super(source);
		this.name = name;
		this.occupation = occupation;
		this.AgeCategory = ageCat;
		this.empCat = empCat;
		this.taxId = taxId;
		this.bdCitizen = bdCitizen;
		this.gender = gender;
	}
	
	
	public String getTaxId() {
		return taxId;
	}

	public boolean isBdCitizen() {
		return bdCitizen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getGender() {
		return gender;
	}

	public int getAgeCategory(){
		return AgeCategory;
	}
	public String getEmployeeCategory(){
		return empCat;
	}
}
