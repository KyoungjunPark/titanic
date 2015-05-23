package titanic;

import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import util.GroupNode;
import model.ModelManager;

public class FileTree extends JTree implements Controllerable {

	private DefaultMutableTreeNode root;

	public FileTree() {
		setModel(null);

	}

	public void makeTree() {
		ModelManager.sharedModelManager().setTitanicModelID(0);
		root = ModelManager.sharedModelManager().getCurrentTitanicModel()
				.getGroupNode().getTreeNode();
		setTreeTag();
		setModel(new DefaultTreeModel(root));
	}

	/*
	 * This function's purpose is to assign tags witch are differentiate a root
	 * node, sub root node, leaf node
	 */
	private void setTreeTag() {
		Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeEnumeration.nextElement();

		JOptionPane.showMessageDialog(null, root.getDepth());

	}

	public DefaultMutableTreeNode findNode(DefaultMutableTreeNode root,
			String search) {
		
		Enumeration nodeEnumeration = root.breadthFirstEnumeration();
		while (nodeEnumeration.hasMoreElements()) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodeEnumeration
					.nextElement();
			String found = (String) node.getUserObject();
			if (search.equals(found)) {
				return node;
			}
		}
		return null;
	}

	@Override
	public void setAction(String title, ActionListener action) {

	}
}
