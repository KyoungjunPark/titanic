package titanic;

import model.Event;
import model.EventManager;

public class RightPanelController extends CenterPanelController {

	private RightPanel rightPanel;
	private WholeDSMTable table;
	
	RightPanelController() {
	}

	RightPanelController(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}
	
	protected void redrawPanel(){
		this.rightPanel.redrawPanel();
	}
	
	protected int getID(){
		return this.rightPanel.getID();
	}
}
