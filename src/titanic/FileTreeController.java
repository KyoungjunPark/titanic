package titanic;

import java.util.ArrayList;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import model.EventManager;

public class FileTreeController extends LeftPanelController {

	private FileTree treeFile;

	public FileTreeController(FileTree treeFile) {
		this.treeFile = treeFile;
		init();
	}

	private void init() {
		treeFile.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				ArrayList<String> tag = new ArrayList<>();
				TreePath[] paths = treeFile.getSelectionModel().getSelectionPaths();
				ArrayList<DefaultMutableTreeNode> nodes = new ArrayList<>();
				
				if(paths.length == 0) return;
				for (TreePath path : paths) {

					DefaultMutableTreeNode node = (DefaultMutableTreeNode) path
							.getLastPathComponent();
					nodes.add(node);
					
					if (node.isRoot()) {
						tag.add("Root");

					} else if (node.isLeaf()) {
						tag.add("Leaf");
						if (node.getPreviousLeaf() == null
								|| ((DefaultMutableTreeNode) node.getParent())
										.getFirstChild() == node) {
							// if this node is first leaf of parent's(disable
							// move
							// up icon)
							tag.add("TopItem");
						} else if (node.getNextLeaf() == null
								|| ((DefaultMutableTreeNode) node.getParent())
										.getLastChild() == node) {
							// if this node is last leaf of parent's(disable
							// move
							// down icon)
							tag.add("BottomItem");
						} else {
							tag.add("Item");
						}

					} else {
						tag.add("Not Leaf");
						if (node.getPreviousSibling() == null) {
							// if this node is first node of parent's(disable
							// move
							// up icon)
							tag.add("TopSubRoot");

						} else if (node.getNextSibling() == null) {
							// if this node is first node of parent's(disable
							// move
							// up icon)
							tag.add("BottomSubRoot");

						} else {
							tag.add("SubRoot");
						}
					}

				}
				//design issue ( in titan root->folder & folder->root selection status is different!
			
				//case : ExpandAllButton
				EventManager.callEvent("expandAllButtonEnable");
				/*must enabled*/
			
				//case : CollapseAllButton
				/*must enabled*/
				EventManager.callEvent("collapseAllButtonEnable");
				
				//case : GroupButton
				Boolean bool = true;
				TreeNode temp = nodes.get(0).getParent();
				
				for(int i = 1 ; i <nodes.size(); i++){
					if(temp != nodes.get(i).getParent()){
						bool = false;
						break;
					}
				}	
				if(tag.contains("Root")){
					//disabled
					EventManager.callEvent("groupButtonDisable");
				}else if(tag.contains("Leaf") && tag.contains("Not Leaf")){
					//disabled
					EventManager.callEvent("groupButtonDisable");
				}else if(!bool){
					//if bool is false -> group disabled
					EventManager.callEvent("groupButtonDisable");
					
				}else{
					//enabled
					EventManager.callEvent("groupButtonEnable");
				}
			
				//case : UngroupButton
				if(tag.contains("Leaf")){
					//disabled
					EventManager.callEvent("ungroupButtonDisable");
					
				}
				else if(tag.contains("Leaf") && tag.contains("Not Leaf")){
					//disabled
					EventManager.callEvent("ungroupButtonDisable");
				}
				else if(tag.contains("Root")){
					//disabled
					EventManager.callEvent("ungroupButtonDisable");
				}else{
					//enabled
					EventManager.callEvent("ungroupButtonEnable");
				}
				
				//case : MoveUpButton
				if(tag.contains("Root")){
					//disabled
					EventManager.callEvent("moveUpButtonDisable");
				}
				else if(tag.contains("TopItem")){
					//disabled
					EventManager.callEvent("moveUpButtonDisable");
				}
				else if(tag.contains("TopSubRoot")){
					//disabled
					EventManager.callEvent("moveUpButtonDisable");
				}else if(tag.contains("Leaf") && tag.contains("Not Leaf")){
					EventManager.callEvent("moveUpButtonDisable");
				}
				else{
					//enabled
					EventManager.callEvent("moveUpButtonEnable");
				}
			
				//case : MoveDownButton
				if(tag.contains("Root")){
					//disabled
					EventManager.callEvent("moveDownButtonDisable");
				}
				else if(tag.contains("BottomItem")){
					//disabled
					EventManager.callEvent("moveDownButtonDisable");
				}
				else if(tag.contains("BottomSubRoot")){
					//disabled
					EventManager.callEvent("moveDownButtonDisable");
				}else if(tag.contains("Leaf") && tag.contains("Not Leaf")){
					EventManager.callEvent("moveDownButtonDisable");
				}else{
					//enabled
					EventManager.callEvent("moveDownButtonEnable");
				}
				//case : DeleteButton
				if(tag.contains("Root")){
					//disabled
					EventManager.callEvent("deleteButtonDisable");
				}else{
					//enabled
					EventManager.callEvent("deleteButtonEnable");
				}
			}
		});

	}

	protected void makeTree() {
		treeFile.makeTree();
	}

	protected void moveUp() {
		treeFile.moveUp();
	}

	protected void moveDown() {
		treeFile.moveDown();

	}

	protected void expandAll(int startingIndex, int rowCount) {
		treeFile.expandAll(startingIndex, rowCount);

	}

	protected void collapseAll() {
		treeFile.collapseAll();

	}

	protected void delete() {
		treeFile.delete();
	}

}
