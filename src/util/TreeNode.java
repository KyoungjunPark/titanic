package util;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by kimjisoo on 5/23/15.
 */
public class TreeNode extends DefaultMutableTreeNode {
    private Node node;
    public TreeNode(String string){
        super(string);
    }
    public TreeNode(String string, Node node){
        this(string);
        this.node = node;
    }
    public String getType(){
        return this.node.getType();
    }
    public Node getNode(){
        return this.node;
    }
}
