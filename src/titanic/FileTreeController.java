package titanic;


import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;


public class FileTreeController extends LeftPanelController {

	private FileTree treeFile;
	private TreePath[] selectedPaths;
	
	public FileTreeController(FileTree treeFile) {
		this.treeFile = treeFile;

		init();
	}

	private void init() {
		
		
		//listener of item selection
		treeFile.getSelectionModel().setSelectionMode(
		TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		treeFile.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				JTree treeSource = (JTree) e.getSource();
			//	System.out.println("Min: " + treeSource.getMinSelectionRow());
			//	System.out.println("Max: " + treeSource.getMaxSelectionRow());
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeFile
						.getLastSelectedPathComponent();

				if (node == null)
					// Nothing is selected.
					return;
				
		
				
				TreePath[] paths = treeFile.getSelectionPaths();
				selectedPaths = paths;
				/*test version
				for(TreePath path : paths){
					System.out.println(path.getLastPathComponent());
					
				}
				*/
			}
		});

	}

	protected void makeTree() {
		treeFile.makeTree();
	}
	protected void moveUp()
	{
		treeFile.moveUp(selectedPaths);
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
