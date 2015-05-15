package titanic;

import javax.swing.JSplitPane;

public class MainPanel extends JSplitPane {
	public MainPanel()
	{
		LeftPanel leftPanel = new LeftPanel();
		setLeftComponent(leftPanel);
		
		RightPanel rightPanel = new RightPanel();
		setRightComponent(rightPanel);
		
	}
}
