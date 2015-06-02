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
          while(true){
        	  if(checkRow()==true){
        		  continue;
        	  }
        	  if(checkColumn()==true){
        		  continue;
        	  }
        	  
          }
        } catch (CreateException e) {
            e.printStackTrace();
        }
        printTest();
    }
    private boolean checkRow(){													//Row가 모두 0인지 검사하여
    	boolean changeRow = false;												//맨 위로 보냄
    	int check=0;
    	for(int i=0; i<dependencyNumber;i++){
    		for(int j=0; j<dependencyNumber;j++){
    			if(dependencyRelationArray.get(i*dependencyNumber+j)==0){
    				check++;
    			}
    		}
    		if(check==dependencyNumber){
    			changeRow=true;
    			moveRowToTopLeftmost(i);
    			return changeRow;
    		}
    		check=0;
    	}
    	return changeRow;
    }
    
    private boolean checkColumn(){												//Column이 모두 0인지 검사하여
    	boolean changeColumn=false;												//맨 아래로 보냄
    	int check=0;
    	for(int i=0; i<dependencyNumber;i++){
    		for(int j=0; j<dependencyNumber;j++){
    			if(dependencyRelationArray.get(j*dependencyNumber+i)==0){
    				check++;
    			}
    		}
    		if(check==dependencyNumber){
    			changeColumn=true;
    			moveRowToBottomRightmost(i);
    			return changeColumn;
    		}
    		check=0;
    	}
    	return changeColumn;
    }
    
    private void moveUpORLeft(int row) throws CreateException {
        if(row == 0) throw new CreateException("Impossible input");

        //dependencyRelationArray's move up or left
        for(int i = 0 ; i < dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i , (row-1)*dependencyNumber+i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber+(row-1));
        }

        //elementsNameArray's move up or left
        Collections.swap(elementsNameArray, row, row - 1);

    }
    private void moveDownORRight(int row) throws CreateException {
        if(row == dependencyNumber -1) throw new CreateException("Impossible input");

        //dependencyRelationArray's move down or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (row+1)*dependencyNumber+i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber+(row+1));
        }
        //elementsNameArray's move down or right
        Collections.swap(elementsNameArray, row, row + 1);
    }
    private void moveRowToBottomRightmost(int row){

        //dependencyRelationArray's row to bottom or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (dependencyNumber-1)*dependencyNumber+i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber+(dependencyNumber-1));
        }

        //elementsNameArray's row to bottom or right
        Collections.swap(elementsNameArray,row, dependencyNumber-1);
    }
    private void moveRowToTopLeftmost(int row){
        //dependencyRelationArray's row to top or left
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber);
        }

        //elementsNameArray's row to top or left
        Collections.swap(elementsNameArray, row, 0);

    }
    private void nthSquare(int nth) throws CreateException {
        if(nth <= 0) throw new CreateException("Impossible input");

        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 0 ; i < dependencyNumber ; i++){
            for(int j = 0 ; j < dependencyNumber ; j++){
                //(i,j)
                int pointSum = 0;
                for(int k = 0 ; k < dependencyNumber; k++){
                  pointSum +=dependencyRelationArray.get(i*dependencyNumber + k)* dependencyRelationArray.get(j +dependencyNumber*k);
                }
                result.add(pointSum);
            }
        }

        this.dependencyRelationArray = result;
    }
    
    private void makeGroupNode()
    {

    }
    //must be deleted
    private void printTest()
    {
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
