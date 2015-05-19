package titanic;

import java.awt.BorderLayout;
import java.awt.print.Book;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class RightPanel extends JPanel {
	private JTabbedPane tabbedPane;
	private JLabel mainLabel;
	private JPanel mainPanel;
	
	public RightPanel(){
		setLayout(new BorderLayout(0,0));
		
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
		
		mainLabel = new JLabel("first°", SwingConstants.CENTER);
		mainPanel = new JPanel();
		mainPanel.add(mainLabel);
		tabbedPane.addTab("1",mainPanel);
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JLabel getMainLabel() {
		return mainLabel;
	}

	public JPanel getMainPanel() {
		return mainPanel;
	}
	
}
