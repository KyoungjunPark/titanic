package controller;

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
		
	}
	protected void settingOpenDSM()
	{
		menubar.settingOpenDSM();
		
	}
}
