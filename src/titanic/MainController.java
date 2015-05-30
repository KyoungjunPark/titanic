package titanic;

import java.io.File;

import model.CreateException;
import model.Event;
import model.EventManager;
import model.ModelManager;
import titanic.BackgroundPanel.MainToolbar;

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
		EventManager.addEvent(new Event("after-open-First-DSM") {
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
                EventManager.callEvent("newDSMRowButtonEnable");

                  
                centerPanelController.getContentsPanelController().addRightPanel();
                
            }
        });
		EventManager.addEvent(new Event("after-open-CLSX") {
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

				EventManager.callEvent("Refresh-TabName");
            }
        });
		EventManager.addEvent(new Event("CLSXStatus"){
			public void action(){
				menuBarController.changeDSMStatus();
				mainToolbarController.changeDSMStatus();
				EventManager.callEvent("expandAllButtonEnable");
				EventManager.callEvent("collapseAllButtonEnable");
				EventManager.callEvent("groupButtonDisable");
				EventManager.callEvent("ungroupButtonDisable");
				EventManager.callEvent("moveUpButtonDisable");
				EventManager.callEvent("moveDownButtonDisable");
				EventManager.callEvent("deleteButtonDisable");

			}
		});

		EventManager.addEvent(new Event("Redraw-Table"){
			public void action(){

				centerPanelController.getContentsPanelController().setShowRowLabels(menubar.getShowRowLabelsState(), ModelManager.sharedModelManager().getCurrentID());
				centerPanelController.getContentsPanelController().redrawPanel();

			}
		});
		EventManager.addEvent(new Event("Refresh-TabName"){
			public void action(){
				centerPanelController.getContentsPanelController().refreshTabName();
			}
		});
		EventManager.addEvent(new Event("FileTree-Load"){
			public void action(){
				centerPanelController.getLeftPanelController().getFileTreeController().makeTree();
			}
		});
		EventManager.addEvent(new Event("FileTree-redraw"){
			public void action(){centerPanelController.getLeftPanelController().getFileTreeController().redrawTree();}
		});
        EventManager.addEvent((new Event("makeDefaultNodes"){
            public void action(String... param){
                int numNode = Integer.parseInt(param[0]);
                centerPanelController.getLeftPanelController().getFileTreeController().makeDefaultNodes(numNode);
            }
        }));
		EventManager.addEvent((new Event("after-open-Edit"){
			public void action(){
				EventManager.callEvent("EditStatus");
				centerPanelController.getContentsPanelController().addRightPanel();
			}
		}));
		EventManager.addEvent((new Event("EditStatus"){
			public void action(){
				menuBarController.changeEditStatus();
				mainToolbarController.changeEditStatus();
			}
		}));
	}
	

	protected void openDSMFile(File openFile)
	{		
		int currentID;
		
		try {
			 currentID = ModelManager.sharedModelManager().createTitanicModel(openFile);
			 ModelManager.sharedModelManager().setCurrentID(currentID);
			 EventManager.callEvent("after-open-First-DSM");
			 EventManager.callEvent("Redraw-Table");
			 EventManager.callEvent("FileTree-Load");
		} catch (CreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void OpenClsxStatus(File openFile) {

        try {
            ModelManager.sharedModelManager().setClsx(openFile);
            EventManager.callEvent("after-open-CLSX");
            EventManager.callEvent("Redraw-Table");
            EventManager.callEvent("FileTree-Load");

        } catch (CreateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
