package GUI;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;

public class PersonTableModel extends AbstractTableModel {

	private List<Person> db;
	
	private String[] colNames ={"ID","Name","Occupation","Age Category","Employment Category","BD Citizen","Tax ID"};
	
	public PersonTableModel() {
    
	}

	public void setData(List<Person> db){
		this.db = db;
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		//return super.getColumnName(column);
		return colNames[column];
	}

	@Override
	public int getColumnCount() {

		return 7;
	}

	@Override
	public int getRowCount() {

		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
        
		Person person = db.get(row);
        
		switch (col) {
		case 0:
			  return person.getP_id();			
        case 1: 
        	  return person.getName();
        case 2:
        	  return person.getOccupation();
        case 3:
        	  return person.getAgeCategory();
        case 4:
        	  return person.getEmpCat();
        case 5:
        	 return person.isBdCitizen();
        case 6:
        	 return person.getTaxId();		
		}
		return null;
	}

}
