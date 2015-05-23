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
		
	}
	private void setAction()
	{
		leftToolbarController = new LeftToolbarController(leftPanel.getToolbar());
		fileTreeController = new FileTreeController(leftPanel.getfileTree());
		
	}
	public void OpenDSMStatus()
	{
		
		
	}
	protected void expandTree() {
		leftPanel.getfileTree();
		fileTreeController.expandAll(leftPanel.getfileTree(), 1, leftPanel.getfileTree().getRowCount());;
	}
	protected void collapseTree() {
		leftPanel.getfileTree();
		fileTreeController.collapseAll(leftPanel.getfileTree(), 1, leftPanel.getfileTree().getRowCount());
	}
}
