package titanic;

import javax.swing.JSplitPane;
import java.awt.Color;

public class MainPanel extends JSplitPane {
	
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	
	public MainPanel()
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
