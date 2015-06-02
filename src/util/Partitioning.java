package util;

import model.CreateException;

import java.util.ArrayList;
import java.util.Collections;

public class Partitioning {

    private int dependencyNumber;
    private ArrayList<Integer> dependencyRelationArray;
    private ArrayList<String> elementsNameArray;

    public Partitioning(int dependencyNumber, ArrayList<Integer> dependencyRelationArray, ArrayList<String> elementsNameArray){
        this.dependencyNumber = dependencyNumber;
        this.dependencyRelationArray = dependencyRelationArray;
        this.elementsNameArray = elementsNameArray;

        printTest();
        doPartitioning();
    }
    private void doPartitioning()
    {

        try {
            //do algorithm
        } catch (CreateException e) {
            e.printStackTrace();
        }
        printTest();
    }
    private void moveUpORLeft(int row) throws CreateException {
        if(row == 0) throw new CreateException("Impossible input");

        //dependencyRelationArray's move up or left
        for(int i = 0 ; i < dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i , (row-1)*dependencyNumber+i);
        }

        //elementsNameArray's move up or left
        Collections.swap(elementsNameArray, row, row - 1);

    }
    private void moveDownORRight(int row) throws CreateException {
        if(row == dependencyNumber -1) throw new CreateException("Impossible input");

        //dependencyRelationArray's move down or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (row+1)*dependencyNumber+i);
        }
        //elementsNameArray's move down or right
        Collections.swap(elementsNameArray, row, row + 1);
    }
    private void moveRowToBottomRightmost(int row){

        //dependencyRelationArray's row to bottom or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (dependencyNumber-1)*dependencyNumber+i);
        }

        //elementsNameArray's row to bottom or right
        Collections.swap(elementsNameArray,row, dependencyNumber-1);
    }
    private void moveRowToTopLeftmost(int row){
        //dependencyRelationArray's row to top or left
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, i);
        }

        //elementsNameArray's row to top or left
        Collections.swap(elementsNameArray, row, 0);

    }
    private ArrayList<Integer> nthSquare(ArrayList<Integer> list, int nth) throws CreateException {
        if(nth <= 0) throw new CreateException("Impossible input");
        for(int i = 1 ; i < nth ; i++){
            list = reculNthSquare(list);
        }
        return list;
    }
    private ArrayList<Integer> reculNthSquare(ArrayList<Integer> list){
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0 ; i < list.size() ; i++){
            for(int j = 0 ; j <  list.size() ; j++){
                //(i,j)
                int pointSum = 0;
                for(int k = 0 ; k <  list.size(); k++){
                    pointSum +=list.get(i* list.size() + k)* list.get(j + list.size()*k);
                }
                result.add(pointSum);
            }
        }
        return result;
    }
    private void makeGroupNode(){

    }
    //must be deleted
    private void printTest() {
        System.out.println("dependencyNumber : " + dependencyNumber);
        System.out.println("dependencyRelationArray");
        for(int i = 0 ; i < dependencyNumber*dependencyNumber ; i++){
            if(i % dependencyNumber == 0) System.out.println();
            System.out.print(dependencyRelationArray.get(i));
        }
        System.out.println("\nelementsNameArray");
        for(String a : elementsNameArray) System.out.println(a);
    }
}
