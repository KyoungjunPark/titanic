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
	private void makeTree()
	{
		treeFile.makeTree();
		
	}
	
}
