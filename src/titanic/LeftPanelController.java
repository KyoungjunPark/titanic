package titanic;

import javax.swing.JPanel;

public class LeftPanelController extends CenterPanelController{
	
	private LeftPanel leftPanel;
	
	public LeftPanelController(){}
	public LeftPanelController(LeftPanel leftPanel){
		this.leftPanel = leftPanel;
		init();
		
	}
	private void init()
	{
	}
	public void OpenDSMStatus()
	{
		
		
	}
	protected void expandTree()
	{
		leftPanel.getfileTree();
		
	}
}
