package util;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class ItemNode extends Node{
    private int index;
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
    public GreenTreeNode getTreeNode(){
        return new GreenTreeNode(this.getName(), this);
    }
    public String getType(){
        return "I";
    }
    public String toString(){
        return "<item name=\""+this.getName()+"\" />\n";
    }
}
