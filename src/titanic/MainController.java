package titanic;

import java.io.File;

import model.EventManager;
import titanic.BackgroundPanel.MainToolbar;
import model.CreateException;
import model.ModelManager;
import model.Event;

import javax.swing.*;

public class MainController {
	
	private MainToolbar toolbar;
	private MenuBar menubar;
	private CenterPanel centerPanel;
	
	private MenuBarController menuBarController;
	private MainToolbarController mainToolbarController;
	private CenterPanelController centerPanelController ;
	

	public MainController(){}
	public MainController(MainToolbar toolbar, MenuBar menubar, CenterPanel centerPanel){
		this.toolbar = toolbar;
		this.menubar = menubar;
		this.centerPanel = centerPanel;

		setControllers();
		setEvent();
	}
	private void setControllers()
	{
		
		menuBarController = new MenuBarController(menubar);
		mainToolbarController = new MainToolbarController(toolbar);
		centerPanelController = new CenterPanelController(centerPanel);
	}
	private void setEvent()
	{
	    /**
	     * DSM파일 or CLSX파일이 실행되면
	     * 1. disable되어 있던 기능(아이콘)들이 활성화 된다.
	     * 2. 좌측 패널에 file tree를 보여준다.
	     *
	     */
		EventManager.addEvent(new Event("after-openDSM") {
            public void action() {
                menuBarController.changeDSMStatus();
                mainToolbarController.changeDSMStatus();
                EventManager.callEvent("expandAllButtonEnable");
                EventManager.callEvent("collapseAllButtonEnable");
                EventManager.callEvent("groupButtonDisable");
                EventManager.callEvent("ungroupButtonDisable");
                EventManager.callEvent("moveUpButtonDisable");
                EventManager.callEvent("moveDownButtonDisable");
                EventManager.callEvent("deleteButtonDisable");
                centerPanelController.getLeftPanelController().getFileTreeController().makeTree();
                centerPanelController.getContentsPanelController().addRightPanel();
                
            }
        });
		EventManager.addEvent(new Event("after-openCLSX") {
            public void action() {
                menuBarController.changeDSMStatus();
                mainToolbarController.changeDSMStatus();
                EventManager.callEvent("expandAllButtonEnable");
                EventManager.callEvent("collapseAllButtonEnable");
                EventManager.callEvent("groupButtonDisable");
                EventManager.callEvent("ungroupButtonDisable");
                EventManager.callEvent("moveUpButtonDisable");
                EventManager.callEvent("moveDownButtonDisable");
                EventManager.callEvent("deleteButtonDisable");
                centerPanelController.getLeftPanelController().getFileTreeController().makeTree();
           
            }
        });

		EventManager.addEvent(new Event("Redraw"){
			public void action(){

				centerPanelController.getContentsPanelController().setShowRowLabels(menubar.getShowRowLabelsState(), ModelManager.sharedModelManager().getCurrentID());
				centerPanelController.getContentsPanelController().redrawPanel();
			}
		});
	}
	

	protected void openDSMFile(File openFile)
	{		
		int currentID;
		
		try {
			 currentID = ModelManager.sharedModelManager().createTitanicModel(openFile);
			 ModelManager.sharedModelManager().setCurrentID(currentID);
			 EventManager.callEvent("after-openDSM");
			 EventManager.callEvent("Redraw");
			 
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	protected void OpenClsxStatus(File openFile)
	{
		
		try {
			ModelManager.sharedModelManager().setClsx(openFile);
			EventManager.callEvent("Redraw");
			
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
