package titanic;

import javax.swing.JPanel;

public class LeftPanelController extends CenterPanelController{
	
	private LeftPanel leftPanel;
	private LeftToolbarController leftToolbarController;
	private FileTreeController fileTreeController;
	
	public LeftPanelController(){}
	public LeftPanelController(LeftPanel leftPanel){
		this.leftPanel = leftPanel;
		setAction();
		init();
		
	}
	private void init()
	{
		//setEvent("expand all");
	}
	private void setAction()
	{
		leftToolbarController = new LeftToolbarController(leftPanel.getToolbar());
		fileTreeController = new FileTreeController(leftPanel.getfileTree());
		
	}
	public void OpenDSMStatus()
	{
		
		
	}
<<<<<<< HEAD
	protected void expandTree()
	{
		leftPanel.getfileTree();
		
=======
	protected void expandTree() {
		leftPanel.getfileTree();
		fileTreeController.expandAll(leftPanel.getfileTree(), 1, leftPanel.getfileTree().getRowCount());;
>>>>>>> f2e2506e0fda85e25f1b79f6585b102242278a93
	}
}
