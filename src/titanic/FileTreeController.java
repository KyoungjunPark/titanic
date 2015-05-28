package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;


import model.EventManager;
import util.GreenTreeNode;

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
				ArrayList<String> tag = new ArrayList<String>();
				
				TreePath[] paths = treeFile.getSelectionModel()
						.getSelectionPaths();
				ArrayList<GreenTreeNode> nodes = new ArrayList<GreenTreeNode>();

				if (paths.length == 0)
					return;

				analyzeNode(paths, tag, nodes);

				// design issue ( in titan root->folder & folder->root selection
				// status is different!

				// case : ExpandAllButton
				EventManager.callEvent("expandAllButtonEnable");
				/* must enabled */

				// case : CollapseAllButton
				/* must enabled */
				EventManager.callEvent("collapseAllButtonEnable");

				// case : GroupButton
				Boolean bool = true;
				GreenTreeNode temp = (GreenTreeNode)nodes.get(0).getParent();

				for (int i = 1; i < nodes.size(); i++) {
					if (temp != nodes.get(i).getParent()) {
						bool = false;
						break;
					}
				}
				if (tag.contains("Root")) {
					// disabled
					EventManager.callEvent("groupButtonDisable");
				} else if (tag.contains("Leaf") && tag.contains("Not Leaf")) {
					// disabled
					EventManager.callEvent("groupButtonDisable");
				} else if (!bool) {
					// if bool is false -> group disabled
					EventManager.callEvent("groupButtonDisable");

				} else {
					// enabled
					EventManager.callEvent("groupButtonEnable");
				}

				// case : UngroupButton
				if(nodes.size()> 1){
					EventManager.callEvent("ungroupButtonDisable");
				}
				else if (tag.contains("Leaf")) {
					// disabled
					EventManager.callEvent("ungroupButtonDisable");

				} else if (tag.contains("Leaf") && tag.contains("Not Leaf")) {
					// disabled
					EventManager.callEvent("ungroupButtonDisable");
				} else if (tag.contains("Root")) {
					// disabled
					EventManager.callEvent("ungroupButtonDisable");
				} else {
					// enabled
					EventManager.callEvent("ungroupButtonEnable");
				}

				// case : MoveUpButton
				if (tag.contains("Root")) {
					// disabled
					EventManager.callEvent("moveUpButtonDisable");
				} else if (tag.contains("TopItem")) {
					// disabled
					EventManager.callEvent("moveUpButtonDisable");
				} else if (tag.contains("TopSubRoot")) {
					// disabled
					EventManager.callEvent("moveUpButtonDisable");
				} else if (tag.contains("Leaf") && tag.contains("Not Leaf")) {
					EventManager.callEvent("moveUpButtonDisable");
				} else {
					// enabled
					EventManager.callEvent("moveUpButtonEnable");
				}

				// case : MoveDownButton
				if (tag.contains("Root")) {
					// disabled
					EventManager.callEvent("moveDownButtonDisable");
				} else if (tag.contains("BottomItem")) {
					// disabled
					EventManager.callEvent("moveDownButtonDisable");
				} else if (tag.contains("BottomSubRoot")) {
					// disabled
					EventManager.callEvent("moveDownButtonDisable");
				} else if (tag.contains("Leaf") && tag.contains("Not Leaf")) {
					EventManager.callEvent("moveDownButtonDisable");
				} else {
					// enabled
					EventManager.callEvent("moveDownButtonEnable");
				}
				// case : DeleteButton
				if (tag.contains("Root")) {
					// disabled
					EventManager.callEvent("deleteButtonDisable");
				} else {
					// enabled
					EventManager.callEvent("deleteButtonEnable");
				}
			}
		});
		treeFile.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				final GreenTreeNode node;
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = treeFile.getClosestRowForLocation(e.getX(),
							e.getY());
					treeFile.setSelectionRow(row);
					node = (GreenTreeNode) ( treeFile
							.getPathForRow(row)).getLastPathComponent();

					if (!node.isLeaf() || node.isRoot()) {

						ActionListener menuListener = new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e1) {

								if (e1.getActionCommand() == "Rename") {
									String answer;

									answer = JOptionPane.showInputDialog(null, "Enter new group name: ", "Group Name", JOptionPane.PLAIN_MESSAGE);

									while(answer.isEmpty()) {
										answer = JOptionPane.showInputDialog(null, "Empty input is not accepted!\n Enter new group name: ", "Group Name", JOptionPane.ERROR_MESSAGE);
									}
									if(answer != null) {
										treeFile.rename(node, answer);
									}
								} else if (e1.getActionCommand() == "Sort") {

									JOptionPane.showMessageDialog(null,
											" Sort was pressed");
								} else if (e1.getActionCommand() == "Duplicate") {

									JOptionPane.showMessageDialog(null,
											" Duplicate was pressed");
								} else {// case : Edit

									JOptionPane.showMessageDialog(null,
											" Edit was pressed");
								}

							}
						};
						PopupMenu popup = new PopupMenu(menuListener);
						popup.show(e.getComponent(), e.getX(), e.getY());
					}

				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	/*
	 * 이 함수는 현재 입력받은 node들이 어떠한 특성을 가지고 있는지 분석해줍니다.
	 * 
	 * @parameter: paths : node들의 path들을 가지고 있습니다. tag : 각각의 node들이 가지고 있느 특성을
	 * 저장하고 있습니다. nodes : 모든 노드를 가지고 있습니다.
	 */
	private void analyzeNode(TreePath[] paths, ArrayList<String> tag,
			ArrayList<GreenTreeNode> nodes) {

		for (TreePath path : paths) {
			
			GreenTreeNode node = (GreenTreeNode) path
					.getLastPathComponent();
			nodes.add(node);

			if (node.isRoot()) {
				tag.add("Root");

			} else if (node.isLeaf()) {
				tag.add("Leaf");
				if (node.getPreviousLeaf() == null
						|| ((GreenTreeNode) node.getParent())
								.getFirstChild() == node) {
					// if this node is first leaf of parent's(disable
					// move
					// up icon)
					tag.add("TopItem");
				} else if (node.getNextLeaf() == null
						|| ((GreenTreeNode) node.getParent())
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
	
	protected void groupTree(String sysMsg) {
		treeFile.groupTree(sysMsg);
	}
	
	protected void unGroupTree() {
		treeFile.unGroupTree();
	}

}
