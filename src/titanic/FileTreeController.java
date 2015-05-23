package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import model.ModelManager;

public class FileTreeController extends LeftPanelController {

	FileTree treeFile;

	public FileTreeController(FileTree treeFile) {
		this.treeFile = treeFile;

		init();
	}

	private void init() {
		treeFile.getSelectionModel().setSelectionMode(
				TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		treeFile.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				JTree treeSource = (JTree) e.getSource();
				System.out.println("Min: " + treeSource.getMinSelectionRow());
				System.out.println("Max: " + treeSource.getMaxSelectionRow());
				System.out.println("Lead: " + treeSource.getLeadSelectionRow());
				System.out.println("Row: " + treeSource.getSelectionRows()[0]);
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeFile
						.getLastSelectedPathComponent();

				if (node == null)
					// Nothing is selected.
					return;

				// JOptionPane.showMessageDialog(null, node);

			}
		});

	}

	protected void makeTree() {
		treeFile.makeTree();

	}

	protected void expandAll(JTree tree, int startingIndex, int rowCount) {
		for (int i = startingIndex; i < rowCount; i++) {
			tree.expandRow(i);
		}

		if (tree.getRowCount() != rowCount) {
			expandAll(tree, rowCount, tree.getRowCount());
		}
	}

	protected void collapseAll(JTree tree, int startingIndex, int rowCount) {
		for (int i = rowCount - 1; i >= startingIndex; i--) {
			tree.collapseRow(i);
		}
		/*
		 * if(tree.getRowCount() != tree.ro) { collapseAll(tree, rowCount,
		 * tree.getRowCount()); }
		 */
	}

}
