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
		setEvent();
		setControllers();
		
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
        
        EventManager.addEvent(new Event("delete"){
        	public void action(){
        		moveUp();
        		
        	}
        	
        });
		
	}
	private void setControllers()
	{
		leftToolbarController = new LeftToolbarController(leftPanel.getToolbar());
		fileTreeController = new FileTreeController(leftPanel.getfileTree());
		
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
}
