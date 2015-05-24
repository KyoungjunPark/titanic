package titanic;

public class CenterPanelController extends MainController{

	private CenterPanel centerPanel;
	private LeftPanelController leftPanelController;
	private ContentsPanelController contentsPanelController;
	
	public LeftPanelController getLeftPanelController() {
		return leftPanelController;
	}
	public ContentsPanelController getContentsPanelController() {
		return contentsPanelController;
	}
	public CenterPanelController(){}
	public CenterPanelController(CenterPanel centerPanel){
		this.centerPanel = centerPanel;
		setControllers();
		
	}
	private void setControllers()
	{
		leftPanelController = new LeftPanelController(centerPanel.getLeftPanel());
		contentsPanelController = new ContentsPanelController(centerPanel.getContentsPanel());       
	}
	
	
	// redraw 
}
