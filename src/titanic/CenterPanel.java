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
		
		contentsPanel = new ContentsPanel();
		setRightComponent(contentsPanel);
		contentsPanel.setBackground(Color.LIGHT_GRAY);
		
	}
	public ContentsPanel getContentsPanel()
	{
		return contentsPanel;
	}
	public LeftPanel getLeftPanel() {
		return leftPanel;
	}
}
