package util;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by KimJiSoo on 15. 5. 19..
 */
public abstract class Node {
    private String name;
    private Node parentNode;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setParentNode(Node parentNode){
        this.parentNode = parentNode;
    }
    public Node getParentNode(){
        return this.parentNode;
    }
    abstract public DefaultMutableTreeNode getTreeNode();
    abstract public void print();
}
