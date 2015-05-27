package model;

/**
 * Created by A on 15. 5. 27..
 */
public class T3{
    private int depth;
    private int first;
    private int last;
    public T3(int depth, int first, int last){
        this.depth = depth;
        this.first = first;
        this.last = last;
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
}
