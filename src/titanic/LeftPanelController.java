package titanic;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Event;
import model.EventManager;
import model.ModelManager;

public class LeftPanelController extends CenterPanelController {

	private LeftPanel leftPanel;
	private LeftToolbarController leftToolbarController;
	private FileTreeController fileTreeController;

	public LeftToolbarController getLeftToolbarController() {
		return leftToolbarController;
	}

	public FileTreeController getFileTreeController() {
		return fileTreeController;
	}

	public LeftPanelController() {
	}

	public LeftPanelController(LeftPanel leftPanel) {
		this.leftPanel = leftPanel;
		setControllers();
		setEvent();

	}

	private void setControllers() {
		leftToolbarController = new LeftToolbarController(
				leftPanel.getToolbar());
		fileTreeController = new FileTreeController(leftPanel.getfileTree());

	}

	private void setEvent() {
		EventManager.addEvent(new Event("expandAll") {
			public void action() {
				expandTree();
			}
		});

		EventManager.addEvent(new Event("collapseAll") {
			public void action() {
				collapseTree();
			}
		});
		
		EventManager.addEvent(new Event("group") {
			public void action() {
				String answer;
				answer = JOptionPane.showInputDialog(null,
						"Enter new group name: ", "Group Name",
						JOptionPane.PLAIN_MESSAGE);

				while (answer != null && answer.isEmpty()) {
					answer = JOptionPane.showInputDialog(null,
							"Empty input is not accepted!\n Enter new group name: ",
							"Group Name", JOptionPane.ERROR_MESSAGE);
				}
				if (answer != null) {
					groupTree(answer);
				}

			}
		});
		
		EventManager.addEvent(new Event("ungroup") {
			public void action() {
				unGroupTree();
			}
		});

		EventManager.addEvent(new Event("moveUp") {
			public void action() {
				moveUp();
			}
		});

		EventManager.addEvent(new Event("moveDown") {
			public void action() {
				moveDown();
			}
		});

		EventManager.addEvent(new Event("delete") {
			public void action() {
				int answer = JOptionPane.showConfirmDialog(null,
						"Are you sure?", "Delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(answer == JOptionPane.OK_OPTION) {
					deleteTree();
				}

			}

		});
		
		EventManager.addEvent(new Event("newDSMRow") {
			public void action() {
				String answer = JOptionPane.showInputDialog(null,
						"Enter new row name: ", "New DSM Row Name",
						JOptionPane.PLAIN_MESSAGE);

				while (answer != null && answer.isEmpty()) {
					answer = JOptionPane.showInputDialog(null,
							"Empty input is not accepted!\n Enter new row name: ",
							"New DSM Row Name", JOptionPane.ERROR_MESSAGE);
				}
				if (answer != null) {
					addNewDSMRow(answer);
				}

			}

		});
		
		//status setting
		EventManager.addEvent(new Event("expandAllButtonEnable") {
			public void action() {
				expandAllButtonEnable();

			}

		});
		EventManager.addEvent(new Event("expandAllButtonDisable") {
			public void action() {
				expandAllButtonDisable();

			}

		});
		EventManager.addEvent(new Event("collapseAllButtonEnable") {
			public void action() {
				collapseAllButtonEnable();

			}

		});
		EventManager.addEvent(new Event("collapseAllButtonDisable") {
			public void action() {
				collapseAllButtonDisable();

			}

		});
		EventManager.addEvent(new Event("groupButtonEnable") {
			public void action() {
				groupButtonEnable();

			}

		});
		EventManager.addEvent(new Event("groupButtonDisable") {
			public void action() {
				groupButtonDisable();

			}

		});
		EventManager.addEvent(new Event("ungroupButtonEnable") {
			public void action() {
				ungroupButtonEnable();

			}

		});
		EventManager.addEvent(new Event("ungroupButtonDisable") {
			public void action() {
				ungroupButtonDisable();

			}

		});
		EventManager.addEvent(new Event("moveUpButtonEnable") {
			public void action() {
				moveUpButtonEnable();

			}

		});
		EventManager.addEvent(new Event("moveUpButtonDisable") {
			public void action() {
				moveUpButtonDisable();

			}

		});
		EventManager.addEvent(new Event("moveDownButtonEnable") {
			public void action() {
				moveDownButtonEnable();

			}

		});
		EventManager.addEvent(new Event("moveDownButtonDisable") {
			public void action() {
				moveDownButtonDisable();

			}

		});
		EventManager.addEvent(new Event("deleteButtonEnable") {
			public void action() {
				deleteButtonEnable();

			}

		});
		EventManager.addEvent(new Event("deleteButtonDisable") {
			public void action() {
				deleteButtonDisable();

			}

		});
		EventManager.addEvent(new Event("newDSMRowButtonEnable") {
			public void action() {
				newDSMRowButtonEnable();

			}

		});
		EventManager.addEvent(new Event("newDSMRowButtonDisable") {
			public void action() {
				newDSMRowButtonDisable();

			}

		});
		EventManager.addEvent(new Event("yassineButtonEnable"){
			public void action(){
				yassineButtonEnable();
			}
		});
		EventManager.addEvent(new Event("yassineButtonDisable"){
			public void action(){
				yassineButtonDisable();
			}
		});

	}

	public void OpenDSMStatus() {

	}
	protected void moveUp() {
		fileTreeController.moveUp();
	}
	protected void moveDown() {
		fileTreeController.moveDown();
	}
	protected void expandTree() {
		fileTreeController.expandAll(0, leftPanel.getfileTree().getRowCount());
	}
	protected void collapseTree() {
		fileTreeController.collapseAll();
	}
	protected void deleteTree() {
		fileTreeController.delete();
	}
	
	protected void groupTree(String sysMsg) {
		fileTreeController.groupTree(sysMsg);
	}
	
	protected void unGroupTree() {
		fileTreeController.unGroupTree();
	}
	
	protected void addNewDSMRow(String newNodeName) {
		ModelManager.sharedModelManager().getCurrentTitanicModel().addNode(newNodeName);
	}
	
	protected void expandAllButtonEnable(){
		leftToolbarController.expandAllButtonEnable();
	}
	protected void expandAllButtonDisable(){
		leftToolbarController.expandAllButtonDisable();
	}
	protected void collapseAllButtonEnable(){
		leftToolbarController.collapseAllButtonEnable();
	}
	protected void collapseAllButtonDisable(){
		leftToolbarController.collapseAllButtonDisable();
	}
	protected void groupButtonEnable(){
		leftToolbarController.groupButtonEnable();
	}
	protected void groupButtonDisable(){
		leftToolbarController.groupButtonDisable();
	}
	protected void ungroupButtonEnable(){
		leftToolbarController.ungroupButtonEnable();
	}
	protected void ungroupButtonDisable(){
		leftToolbarController.ungroupButtonDisable();
	}
	protected void moveUpButtonEnable(){
		leftToolbarController.moveUpButtonEnable();
	}
	protected void moveUpButtonDisable(){
		leftToolbarController.moveUpButtonDisable();
	}
	protected void moveDownButtonEnable(){
		leftToolbarController.moveDownButtonEnable();
	}
	protected void moveDownButtonDisable(){
		leftToolbarController.moveDownButtonDisable();
	}
	protected void deleteButtonEnable(){
		leftToolbarController.deleteButtonEnable();
	}
	protected void deleteButtonDisable(){
		leftToolbarController.deleteButtonDisable();
	}
	protected void newDSMRowButtonEnable(){leftToolbarController.newDSMRowButtonEnable();}
	protected void newDSMRowButtonDisable(){
		leftToolbarController.newDSMRowButtonDisable();
	}
	protected void yassineButtonEnable(){leftToolbarController.yassineButtonEnable();}
	protected void yassineButtonDisable(){leftToolbarController.yassineButtonDisable();}
}
