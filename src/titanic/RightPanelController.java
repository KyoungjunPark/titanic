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
	
	protected void makeTable(){
		this.rightPanel.makeTable();
	}
	
	protected int getID(){
		return this.rightPanel.getID();
	}
}
