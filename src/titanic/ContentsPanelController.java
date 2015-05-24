package titanic;

public class ContentsPanelController extends CenterPanelController{

	private ContentsPanel contentsPanel;
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
		rightPanelController = new RightPanelController(contentsPanel.getRightPanel().get(0));
	}
	
	
	private void setEvent(){
		
	}
	
	protected void redrawPanel() {
		rightPanelController.redrawPanel();
	}
	
}
