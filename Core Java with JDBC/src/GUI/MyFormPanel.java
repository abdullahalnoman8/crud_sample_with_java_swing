package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MyFormPanel extends JPanel {
	
	private JLabel nameLabel;
	private JLabel occupationLabel;
	private JTextField nameField;
	private JTextField occupationField;
	private JButton okButton;
	private FormListener formlistener;
	private JList ageList;
	private JComboBox empCombo;
	private JCheckBox cityzenCheck;
	private JTextField taxfield;
	private JLabel taxLabel;
	
	
	private JRadioButton maleRadio;
	private JRadioButton femalradio;
	private ButtonGroup genderGroup;
	
	public MyFormPanel() {
		Dimension dim = getPreferredSize();
		  dim.width =250;
		  setPreferredSize(dim);
		System.out.println(dim);
		//Setting the Label and field and button
		
		nameLabel = new JLabel("Name : ");
		occupationLabel = new JLabel("Occupation : ");
		
		nameField = new JTextField(10);
		occupationField = new JTextField(10);
		
		okButton = new JButton("OK");
		ageList = new JList();
		empCombo = new JComboBox();
		cityzenCheck = new JCheckBox();
		taxfield = new JTextField(10);
		taxLabel = new JLabel("Tax ID: ");
		
		maleRadio = new JRadioButton("male");
		femalradio = new JRadioButton("female");
		genderGroup = new ButtonGroup();
		
		maleRadio.setActionCommand("male");
		femalradio.setActionCommand("female");
		maleRadio.setSelected(true);
		
		// SetUp Mnemonics
		okButton.setMnemonic(KeyEvent.VK_O);
		nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
		nameLabel.setLabelFor(nameField);
		
		
		// Set up List Box
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0, "Under 18"));
		ageModel.addElement(new AgeCategory(1, "18 to 65"));
		ageModel.addElement(new AgeCategory(2, "65 or avobe"));
		
		
		ageList.setModel(ageModel);
		ageList.setPreferredSize(new Dimension(115, 60));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);
		
		// Set Up combobox
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("Employed");
		empModel.addElement("Self-employed");
		empModel.addElement("Unemployed");
		empCombo.setModel(empModel);
		empCombo.setEditable(true);
		
		// Set Up tax ID
		taxLabel.setEnabled(false);
		taxfield.setEnabled(false);
		
		cityzenCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean isTicked = cityzenCheck.isSelected();
				taxLabel.setEnabled(isTicked);
				taxfield.setEnabled(isTicked);
			}
		});

		////  Set up gender radios
		genderGroup.add(maleRadio);
		genderGroup.add(femalradio);
		
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String  name = nameField.getText();
				String  occupation = occupationField.getText();
				//For AgeList
				//String ageCat = (String) ageList.getSelectedValue();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String empCat = (String) empCombo.getSelectedItem();
				String taxId = taxfield.getText();
				boolean bdCitizen = cityzenCheck.isSelected();
				
				
				String gender = genderGroup.getSelection().getActionCommand();
				
				
				System.out.println(empCat);
				System.out.println(ageCat.getId());
				
				// Calling the MyFormEvent 
				MyFormEvent ev = new MyFormEvent(this, name, occupation,ageCat.getId(),empCat,taxId,bdCitizen,gender);
				
				if (formlistener !=null) {
					formlistener.formEventOccured(ev);
				}
				
			}
		});
		
		//    Setting the Border 
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outterBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		setBorder(BorderFactory.createCompoundBorder(outterBorder, innerBorder));
		
		layoutComponents();
		
	}

	public void layoutComponents(){

		// Now For add the label,fild and button
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		
		
		/////////   First Row //////
		gc.gridy =0;
		
		gc.weightx =1;
		gc.weighty =0.1;
		
		gc.gridx =0;
		
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_END; 
		gc.insets = new Insets(0, 0, 0, 5);
		add(nameLabel, gc);
		
		gc.gridx =1;
		gc.gridy =0;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameField, gc);
		
		//////------------ Second Row --------------////
		
		gc.gridy++;
		
		gc.weightx =1;
		gc.weighty =0.2;
		
		//gc.gridy =1;
		gc.gridx =0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(occupationLabel, gc);
		
		gc.gridy =1;
		gc.gridx =1;
		gc.anchor = GridBagConstraints.LINE_START; 
		gc.insets = new Insets(0, 0, 0, 0);
		add(occupationField, gc);
		
		/// ----------------- Third Row ------------////
		gc.gridy++;
		
		gc.weightx =1;
		gc.weighty =0.2;
		
		
		gc.gridx =0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Age : "), gc);
		
		
		//gc.gridy = 2;
		gc.gridx =1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; 
		gc.insets = new Insets(0, 0, 0, 0);
		add(ageList, gc);
		
		// / ----------------- Third(Next for Combo) Row ------------////
		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx =0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Employee : "), gc);
		
		// gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(empCombo, gc);

		//----------For CheckBox

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx =0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("BD Citizen : "), gc);
		
		// gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(cityzenCheck, gc);

		// For Check Field Next Row

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx =0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(taxLabel, gc);
		
		// gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(taxfield, gc);
		
		/////--------------- Next Row ---------------///

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.05;

		gc.gridx =0;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 5);
		add(new JLabel("Gender : "), gc);
		
		// gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(maleRadio, gc);
		
		 //////////// ---------- Next Row ------------
		

		gc.gridy++;

		gc.weightx = 1;
		gc.weighty = 0.2;
		
		// gc.gridy = 2;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(femalradio, gc);
		
		// --------------- Fourth (Next) Row ----------- //
		
		gc.gridy++;
		
		gc.weightx =1;
		gc.weighty =3;
		
		//gc.gridy = 3;
		gc.gridx =1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START; 
		gc.insets = new Insets(0, 0, 0, 0);
		add(okButton, gc);
		
		
	}
	
	public void setFormListener(FormListener formListener) {
		this.formlistener = formListener;
	}
}

class AgeCategory{
	private int id;
	private String text;

	public AgeCategory(int id, String text) {

		this.id = id;
		this.text = text;
	}
	
	public String toString(){
		return text;
	}
	public int getId(){
		return id;
	}
}
