package titanic;

import javax.swing.JPanel;

public class LeftPanelController extends CenterPanelController{
	
	private LeftPanel leftPanel;
	private LeftToolbarController leftToolbarController;
	private FileTreeController fileTreeController;
	
	public LeftToolbarController getLeftToolbarController() {
		return leftToolbarController;
	}
	public FileTreeController getFileTreeController() {
		return fileTreeController;
	}
	
	public LeftPanelController(){}
	public LeftPanelController(LeftPanel leftPanel){
		this.leftPanel = leftPanel;
		setControllers();
		
	}
	private void setControllers()
	{
		leftToolbarController = new LeftToolbarController(leftPanel.getToolbar());
		fileTreeController = new FileTreeController(leftPanel.getfileTree());
		
	}
	public void OpenDSMStatus()
	{
		
		
	}
	protected void expandTree() {
		fileTreeController.expandAll(leftPanel.getfileTree(), 0, leftPanel.getfileTree().getRowCount());;
	}
	protected void collapseTree() {
		fileTreeController.collapseAll(leftPanel.getfileTree(), 0, leftPanel.getfileTree().getRowCount());
	}
}
