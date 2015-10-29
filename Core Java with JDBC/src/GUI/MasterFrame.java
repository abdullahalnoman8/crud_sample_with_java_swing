package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.Controller;

public class MasterFrame extends JFrame {

	private TextPanel textPanel;
	private ToolBar toolbar;
	private MyFormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
    private TablePanel tablePanel;
    private PrefsDialog prefsDialog;
    private Preferences prefs;
	public MasterFrame() {
		super("My Swing");

		setLayout(new BorderLayout());

		textPanel = new TextPanel();
		toolbar = new ToolBar();
		formPanel = new MyFormPanel();
		tablePanel = new TablePanel();
		// Adding prefsDialog
		prefsDialog = new PrefsDialog(this);
		prefs = Preferences.userRoot().node("db");
		
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
		
		
		controller = new Controller();
	    tablePanel.setData(controller.getPeople());
		tablePanel.setPersonTableListener(new PersonTableListener() {	
			@Override
			public void rowDeleted(int row) {
				controller.removePerson(row);
				//System.out.println(row);
				
			}
		});
		
		// setting text panel
		// toolbar.setTextPanel(textPanel);
		// setting textpanel using interface
		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				textPanel.appendText(text);

			}
		});
		//------Saving prefsData
		prefsDialog.setPrefsListener(new PrefsListener() {		
			@Override
			public void preferenceSet(String user, String password, int port) {
				System.out.println(user + " : "+new String(password));
				prefs.put("user", user);
				prefs.put("password",password );
				prefs.putInt("port",port);
			}
		});
		
		String user = prefs.get("user", "");
		String password = prefs.get("password", "");
		Integer port = prefs.getInt("port", 3306);
		
		
		prefsDialog.setDefaults(user, password, port);
		
		formPanel.setFormListener(new FormListener() {

			@Override
			public void formEventOccured(MyFormEvent e) {
                /*
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmployeeCategory();
				String gender = e.getGender();

				textPanel.appendText("Name: " + name + " : " + "Occupation"
						+ " : " + occupation + ":" + ageCat + " : " + empCat
						+ "\n");

				System.out.println(gender);
				*/
				controller.addPerson(e);
				tablePanel.refresh();

			}
		});

		setJMenuBar(createMenuBar());

		add(toolbar, BorderLayout.NORTH);
		//add(textPanel, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(500, 400));
		setSize(600, 500);
		setVisible(true);
	}

	private JMenuBar createMenuBar() {

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu windowMenue = new JMenu("Window");
		JMenuItem prefsItem = new JMenuItem("Preferences");

		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.addSeparator();
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		JMenu showMenu = new JMenu("Show");

		// JMenuItem showFormItem = new JMenuItem("Person Form");
		JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		JCheckBoxMenuItem showsecondFormItem = new JCheckBoxMenuItem("Exta");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		showMenu.addSeparator();
		showMenu.add(showsecondFormItem);
		windowMenue.add(showMenu);
		windowMenue.add(prefsItem);

		menuBar.add(fileMenu);
		menuBar.add(windowMenue);

		prefsItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prefsDialog.setVisible(true);
				
			}
		});
		
		showFormItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				formPanel.setVisible(menuItem.isSelected());
			}
		});

		///// Mnemonics and Accelerator
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		
		prefsItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));
		
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				ActionEvent.CTRL_MASK));
		
		
		// For Import Button
		importDataItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				 if ((fileChooser.showOpenDialog(MasterFrame.this)==JFileChooser.APPROVE_OPTION)) {
					 try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MasterFrame.this, "Could not load data from file", "Error", JOptionPane.ERROR_MESSAGE);
					}
					// System.out.println(fileChooser.getSelectedFile());
				}
				
			}
		});
		// For Export Button
		exportDataItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ((fileChooser.showOpenDialog(MasterFrame.this)==JFileChooser.APPROVE_OPTION)) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MasterFrame.this,"Could not save data to file","Error",JOptionPane.ERROR_MESSAGE);
					}
					//System.out.println(fileChooser.getSelectedFile());
				}
				
			}
		});
		
		// For Exiting from the application
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int action = JOptionPane.showConfirmDialog(MasterFrame.this,
						"Do you really want to exit the application",
						"Confarmation Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}

			}
		});

		return menuBar;
	}
}
