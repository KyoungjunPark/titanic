package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.CreateException;
import titanic.CenterPanel;
import titanic.MenuBar;
import titanic.BackgroundPanel.MainToolbar;

public class MainController {
	
	private static MainToolbar toolbar;
	private static MenuBar menubar;
	private static CenterPanel mainPanel;
	
	public MainController(){}
	public MainController(MainToolbar toolbar, MenuBar menubar, CenterPanel mainPanel){
		MainController.toolbar = toolbar;
		MainController.menubar = menubar;
		MainController.mainPanel = mainPanel;
		
		setControllers();
		
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

    /**
     * OpenDSM이 실행되면 disable되어 있던 기능(아이콘)들이 활성화 된다.
     *
     */
	protected void OpenDSMStatus()
	{
		menubar.OpenDSMStatus();
		toolbar.OpenDSMStatus();
		mainPanel.getLeftPanel().getToolbar().OpenDSMStatus();
		
	}

}
