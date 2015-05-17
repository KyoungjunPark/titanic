package titanic;

import java.awt.BorderLayout;
import java.awt.print.Book;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class RightPanel extends JPanel {
	
	public RightPanel(){
		setLayout(new BorderLayout(0,0));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane);
		
		JLabel mainLabel = new JLabel("Ã¹¹øÂ°", SwingConstants.CENTER);
		JPanel mainPanel = new JPanel();
		mainPanel.add(mainLabel);
		tabbedPane.addTab("1",mainPanel);
	}
}
