package titanic;

public class ContentsPanelController extends CenterPanelController{

	private ContentsPanel contentsPanel;
	
	// RightPanelController 없애면서 없앨 예정
	private RightPanelController rightPanelController;
	
	public ContentsPanelController(){}
	
	public ContentsPanelController(ContentsPanel contentsPanel)
	{
		this.contentsPanel = contentsPanel;
		setEvent();
	}
	
	private void setController() {
		//여기 파라미터 없어도 되나요...?
		
		
		// RightPanelController 없애면서 없어질 예정
		rightPanelController = new RightPanelController();
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
}
