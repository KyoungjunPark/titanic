package util;

/**
 * Created by KimJiSoo on 15. 5. 19..
 */
public abstract class Node {
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    abstract public void print();
}
