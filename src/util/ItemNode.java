package util;

/**
 * Created by kimjisoo on 5/19/15.
 */
public class ItemNode extends Node{
    public ItemNode(){

    }
    public ItemNode(String name){
        this.setName(name);
    }
    public void print(){
        System.out.println(this);
    }
    public String toString(){
        return "item "+this.getName();
    }
}
