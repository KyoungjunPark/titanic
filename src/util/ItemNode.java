package util;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class ItemNode extends Node{
    public ItemNode(){

    }
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
    public void print(){
        System.out.println(this);
    }
    public String toString(){
        return "item "+this.getName();
    }
}
