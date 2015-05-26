package util;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class ItemNode extends Node{
    public ItemNode(){}
    public ItemNode(Node node, String name){
        this.setParentNode(node);
        this.setName(name);
    }
    public ItemNode(Node node){
        this(node, "");
    }
    public ItemNode(String name){
        this(null, name);
    }
    public TreeNode getTreeNode(){
        return new TreeNode(this.getName(), this);
    }
    public String getType(){
        return "I";
    }
    public String toString(){
        return "<item name=\""+this.getName()+"\" />\n";
    }
}
