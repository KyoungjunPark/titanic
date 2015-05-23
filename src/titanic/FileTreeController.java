package titanic;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import model.EventManager;
import util.TreeNode;

public class FileTreeController extends LeftPanelController {

	private FileTree treeFile;
	private TreePath[] selectedPaths;

	public FileTreeController(FileTree treeFile) {
		this.treeFile = treeFile;

		init();
	}

	private void init() {

		treeFile.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
						.getPath().getLastPathComponent();

				if (node.isRoot()) {
					EventManager.callEvent("changeRootStatus");

				} else if (node.isLeaf()) {
					/*
					 * test version System.out.println("leaf & top leaf : " +
					 * node.getPreviousLeaf());
					 * System.out.println("leaf & Bottom leaf : "
					 * +node.getNextLeaf());
					 * System.out.println("getFirstLeaf : " +
					 * ((DefaultMutableTreeNode
					 * )node.getParent()).getFirstChild());
					 * System.out.println("getLastLeaf : " +
					 * ((DefaultMutableTreeNode
					 * )node.getParent()).getLastChild());
					 */
					if (node.getPreviousLeaf() == null
							|| ((DefaultMutableTreeNode) node.getParent())
									.getFirstChild() == node) {
						// if this node is first leaf of parent's(disable move
						// up icon)
						EventManager.callEvent("changeItemStatusTop");
					} else if (node.getNextLeaf() == null
							|| ((DefaultMutableTreeNode) node.getParent())
									.getLastChild() == node) {
						// if this node is last leaf of parent's(disable move
						// down icon)
						EventManager.callEvent("changeItemStatusBottom");
					} else {
						EventManager.callEvent("changeItemStatus");
					}

				} else {
					System.out.println("Node & top Node : "
							+ node.getPreviousSibling());
					System.out.println("Node & Bottom Node : "
							+ node.getNextSibling());
					if (node.getPreviousSibling() == null) {
						// if this node is first node of parent's(disable move
						// up icon)
						EventManager.callEvent("changeSubRootStatusTop");

					} else if (node.getNextSibling() == null) {
						// if this node is first node of parent's(disable move
						// up icon)
						EventManager.callEvent("changeSubRootStatusBottom");

					} else {
						EventManager.callEvent("changeSubRootStatus");
					}
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

	protected void expandAll(FileTree tree, int startingIndex, int rowCount) {
		treeFile.expandAll(tree, startingIndex, rowCount);

	}

	protected void collapseAll(FileTree tree, int startingIndex, int rowCount) {
		treeFile.collapseAll(tree, startingIndex, rowCount);

	}

	protected void delete() {
		treeFile.delete();
	}

}
