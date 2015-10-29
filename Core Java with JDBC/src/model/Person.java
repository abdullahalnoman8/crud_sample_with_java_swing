package model;

import java.io.Serializable;

public class Person implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7385004890132860466L;

	//private static int count =0;
	//Since Database id is auto increment os count will be start from 1
	private static int count =1;
	
	private int P_id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory empCat;
	private String taxId;
	private boolean bdCitizen;
	private Gender gender;

	public Person(String name, String occupation, AgeCategory ageCategory,
			EmploymentCategory employmentCategory, String taxId,
			boolean bdCitizen, Gender gender) {

		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCat = employmentCategory;
		this.taxId = taxId;
		this.bdCitizen = bdCitizen;
		this.gender = gender;
		
		this.P_id=count;
		count++;
	}
	
	public int getP_id() {
		return P_id;
	}

	public void setP_id(int p_id) {
		P_id = p_id;
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

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public EmploymentCategory getEmpCat() {
		return empCat;
	}

	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public boolean isBdCitizen() {
		return bdCitizen;
	}

	public void setBdCitizen(boolean bdCitizen) {
		this.bdCitizen = bdCitizen;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
