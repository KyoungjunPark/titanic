package titanic;

import java.io.File;

import model.EventManager;
import titanic.BackgroundPanel.MainToolbar;
import model.CreateException;
import model.ModelManager;
import model.Event;

import javax.swing.*;

public class MainController {
	
	private int currentID;

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

		currentID = ModelManager.sharedModelManager().getCurrentID();
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
	     * DSM파일이 실행되면
	     * 1. disable되어 있던 기능(아이콘)들이 활성화 된다.
	     * 2. 좌측 패널에 file tree를 보여준다.
	     *
	     */
		EventManager.addEvent(new Event("after-open") {
            public void action() {
                menuBarController.changeDSMStatus();
                mainToolbarController.changeDSMStatus();
                centerPanelController.getLeftPanelController().getLeftToolbarController().changeDSMStatus();
                centerPanelController.getLeftPanelController().getFileTreeController().makeTree();

            }
        });


        
<<<<<<< HEAD
=======
        EventManager.addEvent(new Event("collapseAll") {
            public void action() {
            	centerPanelController.getLeftPanelController().collapseTree();
            }
        });
        
        EventManager.addEvent(new Event("delete") {
            public void action() {
            	centerPanelController.getLeftPanelController().deleteTree();
            }
        });
>>>>>>> aa9ccd050c2f52488b6e24a03758dd53fd4d6bb9
		
	}
	

	protected void openDSMFile(File openFile)
	{
		int currentID;
		
		try {
			currentID = ModelManager.sharedModelManager().createTitanicModel(openFile);
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	protected void OpenClsxStatus(File openFile)
	{
		try {
			ModelManager.sharedModelManager().setFile(openFile);
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
