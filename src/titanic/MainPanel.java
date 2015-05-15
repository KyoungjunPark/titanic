package titanic;

import javax.swing.JSplitPane;

public class MainPanel extends JSplitPane {
	public MainPanel()
	{
		LeftScreen leftScreen = new LeftScreen();
		setLeftComponent(leftScreen);
		
		RightScreen rightScreen = new RightScreen();
		setRightComponent(rightScreen);
		
	}
}
