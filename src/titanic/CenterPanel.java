package titanic;

import javax.swing.JSplitPane;

import java.awt.Color;

public class CenterPanel extends JSplitPane{
	
	private LeftPanel leftPanel;
	//private RightPanel rightPanel;
	private ContentsPanel contentsPanel;
	
	public CenterPanel()
	{
		leftPanel = new LeftPanel();
		setLeftComponent(leftPanel);
		
		/*
		rightPanel = new RightPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		setRightComponent(rightPanel);
		*/
		contentsPanel = new ContentsPanel();
		setRightComponent(contentsPanel);
		contentsPanel.setBackground(Color.LIGHT_GRAY);
	}
	public LeftPanel getLeftPanel() {
		return leftPanel;
	}
}
