package titanic;

import java.io.File;

import titanic.BackgroundPanel.MainToolbar;
import model.CreateException;
import model.ModelManager;
import model.Event;

import javax.swing.*;

public class MainController {
	
	private int currentID;

	private MainToolbar toolbar;
	private MenuBar menubar;
	private CenterPanel mainPanel;

	public MainController(){}
	public MainController(MainToolbar toolbar, MenuBar menubar, CenterPanel mainPanel){
		this.toolbar = toolbar;
		this.menubar = menubar;
		this.mainPanel = mainPanel;

		setEvent();
		setControllers();

		currentID = ModelManager.sharedModelManager().getCurrentID();

	
	}
	
	private void setEvent()
	{
	
	
	    /**
	     * DSM파일이 실행되면
	     * 1. disable되어 있던 기능(아이콘)들이 활성화 된다.
	     * 2. 좌측 패널에 file tree를 보여준다.
	     *
	     */
		ModelManager.sharedModelManager().addEvent(new Event("after-open"){ public void action(){
			menubar.OpenDSMStatus();
			toolbar.OpenDSMStatus();
			mainPanel.getLeftPanel().getToolbar().OpenDSMStatus();
			mainPanel.getLeftPanel().getfileTree().makeTree();
		}});
		
		
	}
	private void setControllers()
	{
		
		@SuppressWarnings("unused")
		MenuBarController menuBarController = new MenuBarController(menubar);
		@SuppressWarnings("unused")
		MainToolbarController mainToolbarController = new MainToolbarController(toolbar);
		@SuppressWarnings("unused")
		LeftPanelController mainController = new LeftPanelController(mainPanel.getLeftPanel());
		@SuppressWarnings("unused")
		LeftToolbarController leftToolbarController = new LeftToolbarController(mainPanel.getLeftPanel().getToolbar());
	}
	protected void OpenDSMStatus(File openFile)
	{
		int currentID;
		
		try {
			currentID = ModelManager.sharedModelManager().createTitanicModel(openFile);
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
