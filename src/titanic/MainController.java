package titanic;

import java.io.File;

import titanic.BackgroundPanel.MainToolbar;
import model.CreateException;
import model.ModelManager;

public class MainController {
	
	private int currentID;
	
	private static MainToolbar toolbar;
	private static MenuBar menubar;
	private static CenterPanel mainPanel;
	
	public MainController(){}
	public MainController(MainToolbar toolbar, MenuBar menubar, CenterPanel mainPanel){
		
		MainController.toolbar = toolbar;
		MainController.menubar = menubar;
		MainController.mainPanel = mainPanel;
		
		setControllers();
		currentID = ModelManager.sharedModelManager().getCurrentID();
		
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
     * DSM파일이 실행되면
     * 1. disable되어 있던 기능(아이콘)들이 활성화 된다.
     * 2. 좌측 패널에 file tree를 보여준다.
     *
     */
	protected int sendDataToManager(File openFile)
	{
		try {
			return ModelManager.sharedModelManager().createTitanicModel(openFile);
		} catch (CreateException e) {
			
			e.printStackTrace();
		}
		//must changed!
		return -999;
	}
	protected void OpenDSMStatus(File openFile)
	{
		int currentID;
		
		//status change
		menubar.OpenDSMStatus();
		toolbar.OpenDSMStatus();
		mainPanel.getLeftPanel().getToolbar().OpenDSMStatus();
		
		
		//right panel change
		try {
			currentID = ModelManager.sharedModelManager().createTitanicModel(openFile);
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//ModelManager.sharedModelManager().getGroupNode(id);
		//mainPanel.getLeftPanel().setFileTree();
		
	}

}
