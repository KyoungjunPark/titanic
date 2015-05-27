package titanic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.html.parser.DTDConstants;

import model.EventManager;
import model.ModelManager;

public class ContentsPanelController extends CenterPanelController{

	private ContentsPanel contentsPanel;
	
	// RightPanelController 없애면서 없앨 예정
	private RightPanelController rightPanelController;
	
	public ContentsPanelController(){}
	
	public ContentsPanelController(ContentsPanel contentsPanel)
	{
		this.contentsPanel = contentsPanel;
		//must changed! test version
		setControllers();
		setEvent();
	}

	private void setControllers() {
		//To. 양세현  / From. 한예림 : 여기 파라미터 없어도 되나요...?
		
		//must changed! test version
		// From 세현 : rightPanelController 안쓴다며..? ㅜㅜ
		contentsPanel.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane)e.getSource();
                System.out.println("id : " + ((RightPanel)sourceTabbedPane.getSelectedComponent()).getID());
			    ModelManager.sharedModelManager().setCurrentID(((RightPanel) sourceTabbedPane.getSelectedComponent()).getID());
                EventManager.callEvent("Redraw-FileTree");
            }
		});

	}
	
	private void setEvent(){
		
	}
	
	protected void addRightPanel()
	{
		int id = ModelManager.sharedModelManager().getCurrentID();
        RightPanel panel = new RightPanel(id);
		contentsPanel.addRightPanel(panel);
        contentsPanel.setSelectedComponent(panel);
	}

	protected void redrawPanel() {
		//contentsPanel.regetTableData(ModelManager.sharedModelManager().getCurrentID());
		contentsPanel.drawTableAtTab(ModelManager.sharedModelManager().getCurrentID());
	}
	

	public void setShowRowLabels(boolean state, int tabIndex) {
		contentsPanel.setShowRowLabels(state, tabIndex);
	}

	public void refreshTabName() {
		contentsPanel.refreshTabName();
	}
	/* From 세현 : 주석처리 내가 해놨음!
	protected void redrawPanel() {
		rightPanelController.redrawPanel();
	}
	*/
}
