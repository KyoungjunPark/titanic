package titanic;

import javax.swing.JSplitPane;
import java.awt.Color;

public class MainPanel extends JSplitPane {
	public MainPanel()
	{
		LeftPanel leftPanel = new LeftPanel();
		setLeftComponent(leftPanel);
		
		RightPanel rightPanel = new RightPanel();
		rightPanel.setBackground(Color.LIGHT_GRAY);
		setRightComponent(rightPanel);
		
	}
}
