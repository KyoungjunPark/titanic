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
	protected void expandAll(FileTree tree, int startingIndex, int rowCount) {
		treeFile.expandAll(tree, startingIndex, rowCount);
	    
	}
	protected void collapseAll(FileTree tree, int startingIndex, int rowCount) {
		treeFile.collapseAll(tree, startingIndex, rowCount);
		/*
		 * if(tree.getRowCount() != tree.ro) { collapseAll(tree, rowCount,
		 * tree.getRowCount()); }
		 */
	}
	protected void delete(FileTree tree) {
		treeFile.delete(tree);
	}

}
