package titanic;

import javax.swing.JPanel;

import model.Event;
import model.EventManager;

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
		setEvent();
		
	}
	private void setControllers()
	{
		leftToolbarController = new LeftToolbarController(leftPanel.getToolbar());
		fileTreeController = new FileTreeController(leftPanel.getfileTree());
		
	}
	private void setEvent()
	{
        EventManager.addEvent(new Event("expandAll") {
            public void action() {
            	expandTree();
            }
        });
        
        EventManager.addEvent(new Event("collapseAll") {
            public void action() {
            	collapseTree();
            }
        });
        
        EventManager.addEvent(new Event("moveUp"){
        	public void action(){
        		moveUp();
        	} 	
        });
        
        EventManager.addEvent(new Event("changeSubRootStatus"){
        	public void action(){
        		changeSubRootStatus();
        	}
        });
        EventManager.addEvent(new Event("changeItemStatus"){
        	public void action(){
        		changeSubRootStatus();
        	}
        });
       
		
	}

	public void OpenDSMStatus()
	{
		
		
	}
	protected void moveUp(){
		fileTreeController.moveUp();
		
	}	
	protected void expandTree() {
		fileTreeController.expandAll(leftPanel.getfileTree(), 0, leftPanel.getfileTree().getRowCount());
	}
	protected void collapseTree() {
		fileTreeController.collapseAll(leftPanel.getfileTree(), 0, leftPanel.getfileTree().getRowCount());
	}
	protected void deleteTree() {
		fileTreeController.delete(leftPanel.getfileTree());;
	}

	protected void changeSubRootStatus()
	{
		leftToolbarController.changeSubRootStatus();
		
	}
	protected void changeItemStatus()
	{
		leftToolbarController.changeItemStatus();
	}
}
