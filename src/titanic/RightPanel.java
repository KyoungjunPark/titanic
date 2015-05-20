package titanic;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class RightPanel extends JPanel implements Controllerable{
	
	private JTabbedPane tabbedPane;
	private JLabel mainLabel;
	private JPanel mainPanel;
	
	public RightPanel(){
		setLayout(new BorderLayout(0,0));
		
		tabbedPane = new JTabbedPane();
		add(tabbedPane);
		
		mainLabel = new JLabel("first page", SwingConstants.CENTER);
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

	@Override
	public void setAction(String title, ActionListener action) {
		// TODO Auto-generated method stub
		
	}
	
}
