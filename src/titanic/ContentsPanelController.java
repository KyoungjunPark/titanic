package titanic;

public class ContentsPanelController extends CenterPanelController{

	private ContentsPanel contentsPanel;
	private RightPanelController rightPanelController;
	
	public ContentsPanelController(){}
	
	public ContentsPanelController(ContentsPanel contentsPanel)
	{
		this.contentsPanel = contentsPanel;
		setEvent();
	}
	
	private void setController() {
		//여기 파라미터 없어도 되나요...?
		rightPanelController = new RightPanelController();
	}
	
	
	private void setEvent(){
		
	}
	
	protected void redrawPanel() {
		rightPanelController.makeTable();
	}
	
}
