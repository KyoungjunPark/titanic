package titanic;

public class ContentsPanelController extends CenterPanelController{

	private ContentsPanel contentsPanel;
	
	// RightPanelController 없애면서 없앨 예정
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
	

	protected void redrawPanel(int tabIndex) {
		contentsPanel.regetTableData(tabIndex);
		contentsPanel.drawTableAtTab(tabIndex);
	}
	protected int getTop(){
		return this.contentsPanel.TOP;
	}
	protected void redrawPanel() {
		rightPanelController.redrawPanel();
	}
}
