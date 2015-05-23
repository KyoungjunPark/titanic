package titanic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import model.ModelManager;

public class FileTreeController extends LeftPanelController {

	FileTree treeFile;
	
	public FileTreeController(FileTree treeFile){
		this.treeFile = treeFile;
		
		init();
	}
	
	private void init()
	{

		
	}
	protected void makeTree()
	{
		treeFile.makeTree();
		
	}
	protected void expandAll(JTree tree, int startingIndex, int rowCount) {
		for(int i=startingIndex; i<rowCount; i++){
	        tree.expandRow(i);
	    }

	    if(tree.getRowCount()!= rowCount){
	        expandAll(tree, rowCount, tree.getRowCount());
	    }
	}
	protected void collapseAll(JTree tree, int startingIndex, int rowCount) {
		for(int i=rowCount-1; i>=startingIndex; i--) {
			tree.collapseRow(i);
		}
		/*
		if(tree.getRowCount() != tree.ro) {
			collapseAll(tree, rowCount, tree.getRowCount());
		}
		*/
	}
	
}
