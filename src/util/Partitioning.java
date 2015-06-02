package util;

import java.util.ArrayList;

public class Partitioning {

    private int dependencyNumber;
    private ArrayList<Integer> dependencyRelationArray;
    private ArrayList<String> elementsNameArray;

    public Partitioning(int dependencyNumber, ArrayList<Integer> dependencyRelationArray, ArrayList<String> elementsNameArray){
        this.dependencyNumber = dependencyNumber;
        this.dependencyRelationArray = dependencyRelationArray;
        this.elementsNameArray = elementsNameArray;

        doPartitioning();
    }
    private void doPartitioning()
    {

    }
    private ArrayList<Integer> moveUpORLeft()
    {
        return null;
    }
    private ArrayList<Integer> moveDownORRight()
    {
        return null;
    }
    private ArrayList<Integer> moveRowToBottom(int row){
        return null;
    }
    private ArrayList<Integer> moveRowToTop(int row){
        return null;
    }
    private ArrayList<Integer> nthSquare()
    {
        return null;
    }
    private void makeGroupNode()
    {

    }
}
