package titanic;

import javax.swing.JSplitPane;

import java.awt.Color;
import java.awt.event.ActionListener;

public class CenterPanel extends JSplitPane{
	
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	
	public CenterPanel()
	{
		leftPanel = new LeftPanel();
		setLeftComponent(leftPanel);
		
		rightPanel = new RightPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		setRightComponent(rightPanel);
		
	}
	public LeftPanel getLeftPanel() {
		return leftPanel;
	}

	public RightPanel getRightPanel() {
		return rightPanel;
	}
}
