package titanic;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

public class ContentsPanel extends JTabbedPane{
	
	private ArrayList<RightPanel> contents;
	
	public ContentsPanel(){
		contents = new ArrayList<RightPanel>();
		
		/* tab feature test 
		JLabel mainLabel;
		JPanel mainPanel;
		
		mainLabel = new JLabel("first page", SwingConstants.CENTER);
		mainPanel = new JPanel();
		mainPanel.add(mainLabel);
		addTab("1",mainPanel);
		*/
	}
	public void addRightPanel(RightPanel rightPanel)
	{
		contents.add(rightPanel);
	}
	
}
