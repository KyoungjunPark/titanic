package titanic;

import java.util.ArrayList;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.EventManager;

public class FileTreeController extends LeftPanelController {

	private FileTree treeFile;


	public FileTreeController(FileTree treeFile) {
		this.treeFile = treeFile;
		init();
	}

	private void init() {
		treeFile.addTreeSelectionListener(new TreeSelectionListener() {
			//must consider status changed........(multiple icon problem..)
			/*
			 * tag에 따라 현재 적용할 Status를 결정한다. 
			 * if tag -> changeRootStatus = node가 Root인 경우
			 * 
			 * if tag -> changeItemStatusTop = leaf의 집합들 중 Top leaf를 포함하는 경우
			 * if tag -> changeItemStatusBottom = leaf의 집합들 중 Bottom leaf를 포함하는 경우
			 * if tag -> changeItemStatusAll = leaf의 집합들 중 Top과 Bottom leaf를 모두 포함하는 경우
			 * if tag -> changeItemStatus = leaf의 집합들 중 Top과 Bottom이 아닌 중간 leaf들만 포함하는 경우
			 * 
			 * 
			 * if tag -> changeSubRootStatusTop = node의 집합들 중 Top node를 포함하는 경우
			 * if tag -> changeSubRootStatusBottom = node의 집합들 중 Bottom node를 포함하는 경우
			 * if tag -> changeSubRootStatusAll = node의 집합들 중 Top과 Bottom node를 모두 포함하는 경우
			 * if tag -> changeSubRootStatus = node의 집합들 중 Top과 Bottom이 아닌 중간 node들만 포함하는 경우
			 */
			@Override
			public void valueChanged(TreeSelectionEvent e) {

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

	protected void collapseAll(int startingIndex, int rowCount) {
		treeFile.collapseAll(startingIndex, rowCount);

	}

	protected void delete() {
		treeFile.delete();
	}

}
