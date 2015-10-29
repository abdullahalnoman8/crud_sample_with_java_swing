package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ToolBar extends JPanel implements ActionListener {

	private JButton helloButton;
	private JButton goodbyeButton;
	//private TextPanel textPanel;
    private StringListener stringlistener;
	public ToolBar() {

		setBorder(BorderFactory.createEtchedBorder());
		
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("GoodBye");

		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(helloButton);
		add(goodbyeButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if (clicked == helloButton) {
			//textPanel.appendText("Hello \n");
			if (stringlistener != null) {
				stringlistener.textEmitted("Hello \n");
			}
		} else if (clicked == goodbyeButton) {
			//textPanel.appendText("Goodbye \n");
			if (stringlistener != null) {
				stringlistener.textEmitted("GoodBye \n");
			}
		}
	}

	// Setting textPanel normally
	/*
	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}
    */
	// Setting textPanel by using interface
	public void setStringListener(StringListener listener) {
       this.stringlistener = listener;
	}

}
