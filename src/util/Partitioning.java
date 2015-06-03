package util;

import model.CreateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;

public class Partitioning {

    private int dependencyNumber;
    private int originDependencyNumber;

    private ArrayList<Integer> dependencyRelationArray;
    private ArrayList<Integer> originDependencyRelationArray;

    private ArrayList<String> elementsNameArray;
    private ArrayList<String> originElementsNameArray;
    
    private ArrayList<String> groupList[];
    
    private int removeRow;
    private int removeColumn;
    private int groupNumber;
    public Partitioning(int originDependencyNumber, ArrayList<Integer> originDependencyRelationArray, ArrayList<String> originElementsNameArray){
        this.originDependencyNumber = originDependencyNumber;
        this.originDependencyRelationArray = originDependencyRelationArray;
        this.originElementsNameArray = originElementsNameArray;

        this.dependencyNumber = originDependencyNumber;
        this.dependencyRelationArray = originDependencyRelationArray;
        this.elementsNameArray = originElementsNameArray;
        
        this.groupList = groupList;
        
        this.groupNumber=0;
        this.removeRow=0;
        this.removeColumn=0;

        int num =3;
        this.dependencyNumber = num;
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(1);
        array.add(0);
        array.add(0);
        array.add(0);
        array.add(1);
        array.add(0);
        array.add(0);
        array.add(0);
        array.add(1);
        this.dependencyRelationArray = array;
        printTest();

        try {
            nthSquare(array);
        } catch (CreateException e) {
            e.printStackTrace();
        }
        printTest();
      //  doPartitioning();
    }
    
    
    private void doPartitioning()
    {

        try {
          while(true){
        	  if(dependencyNumber<=1){
        		  break;
        	  }
        	  if(checkRow()==true){
        		  continue;
        	  }
        	  if(checkColumn()==true){
        		  continue;
        	  }
        	  findLoop(nthSquare(dependencyRelationArray));
        	  
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
        printTest();
    }
    private boolean checkRow(){													//Row�? 모두 0?���? �??��?��?��
    	boolean changeRow = false;												//�? ?���? 보냄
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
    
    private boolean checkColumn(){												//Column?�� 모두 0?���? �??��?��?��
    	boolean changeColumn=false;												//�? ?��?���? 보냄
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
        
        for(int i = 0 ; i <originDependencyNumber; i++){
            Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, ((row+removeRow-1)*originDependencyNumber)+i);
            Collections.swap(originDependencyRelationArray, (i+removeRow)*originDependencyNumber+(row+removeRow), (i+removeRow)*originDependencyNumber+(row+removeRow-1));
        }
        Collections.swap(originElementsNameArray, row+removeRow, row+removeRow-1);
        
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
        
        for(int i = 0 ; i <originDependencyNumber; i++){
            Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, (row+removeRow+1)*originDependencyNumber+i);
            Collections.swap(originDependencyRelationArray, i*originDependencyNumber+(row+removeRow), i*originDependencyNumber+(row+removeRow+1));
        }
    	Collections.swap(originElementsNameArray, row+removeRow, row+removeRow+1);
   	 
        //dependencyRelationArray's move down or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (row+1)*dependencyNumber+i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber+(row+1));
        }
        //elementsNameArray's move down or right
        Collections.swap(elementsNameArray, row, row + 1);
    }
    
    private void moveRowToBottomRightmost(int row){

    	for(int i = 0 ; i <originDependencyNumber; i++){
            Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, originDependencyNumber*(originDependencyNumber-1-removeColumn)+i);
            Collections.swap(originDependencyRelationArray, i*originDependencyNumber+(row+removeRow), i*(originDependencyNumber-1)-removeColumn);
        }
    	Collections.swap(originElementsNameArray, row+removeRow, removeRow+dependencyNumber-1);
   	 
        //dependencyRelationArray's row to bottom or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (dependencyNumber-1)*dependencyNumber+i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber+(dependencyNumber-1));
        }
        //elementsNameArray's row to bottom or right
        Collections.swap(elementsNameArray,row, dependencyNumber-1);
        
        for(int i=0 ; i<dependencyNumber ; i++){
        	dependencyRelationArray.remove(dependencyNumber*dependencyNumber-(i+1));
        }
        for(int i=0 ; i<dependencyNumber-1 ; i++){
        	dependencyRelationArray.remove(i*dependencyNumber+(dependencyNumber-1)-i);
        }
        
        elementsNameArray.remove(dependencyNumber-1);
        dependencyNumber--;
        removeColumn++;
    }
    private void moveRowToTopLeftmost(int row){
        
    	 for(int i = 0 ; i <originDependencyNumber; i++){
             Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, (removeRow*originDependencyNumber)+i);
             Collections.swap(originDependencyRelationArray, (i+removeRow)*originDependencyNumber+(row+removeRow), (i+removeRow)*originDependencyNumber);
         }
    	 Collections.swap(originElementsNameArray, row+removeRow, removeRow);
    	 
    	//dependencyRelationArray's row to top or left
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, i);
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber);
        }
      //elementsNameArray's row to top or left
        Collections.swap(elementsNameArray, row, 0);
        
        for(int i=0 ; i<dependencyNumber ; i++){
        	dependencyRelationArray.remove(i*dependencyNumber-i);
        }
        for(int i=0 ; i<dependencyNumber-1 ; i++){
        	dependencyRelationArray.remove(0);
        }
        
        elementsNameArray.remove(0);
        dependencyNumber--;
        removeRow++;

    }
    private ArrayList<Integer> nthSquare(ArrayList<Integer> list) throws CreateException {
    	 ArrayList<Integer> diagonal = new ArrayList<Integer>();
    	if(list.size() <= 0) throw new CreateException("Impossible input");

        for(int i = 1 ; i < dependencyNumber ; i++){
            list = recurNthSquare(list);
        }
        for(int i = 0 ; i<dependencyNumber ; i++){
        	diagonal.add(list.get(i*dependencyNumber+i));
        }
        return diagonal;
    }
    private ArrayList<Integer> recurNthSquare(ArrayList<Integer> list){
        ArrayList<Integer> result = new ArrayList<Integer>();
       
        for(int i = 0 ; i < dependencyNumber ; i++){
            for(int j = 0 ; j < dependencyNumber ; j++){
                //(i,j)
                int pointSum = 0;
                for(int k = 0 ; k <  dependencyNumber; k++){
                    pointSum +=list.get(i*dependencyNumber + k)* list.get(j + dependencyNumber*k);
                }
                result.add(pointSum);
            }
        }
        return result;
    }
    
    private void findLoop(ArrayList<Integer> diagonal) throws CreateException{
    	ArrayList<Integer> starting = new ArrayList();				//save a starting point of DFS
    	ArrayList<Integer> pathFinder = new ArrayList();			//save path
    	boolean end=false;
    	
    	for(int i=0; i<diagonal.size();i++){
    		if(diagonal.get(i)==0){
    			for(int m=0; m<diagonal.size()-1-i;m++){
						moveDownORRight(i+m);
    			}
    		}
    	}
    	
    	for(int i=0; i<diagonal.size();i++){
    		if(diagonal.get(i)!=0){
    			starting.add(i);
    		}
    	}
    	for(int j=0 ; j<starting.size(); j++){
    		for(int edge = dependencyNumber-1; edge>1 ; edge--){
    			
    			pathFinder.add(starting.get(j));
    				if(DFS(pathFinder, edge).size()==edge+1){
    					end=true;
    					break;
    				}
    			pathFinder.clear();
    		}
    		if(end==true){
    			break;
    		}
    	}
    	for(int group=0; group<pathFinder.size()-1 ; group++){
    		groupList[groupNumber].add(elementsNameArray.get(pathFinder.get(group)));
    		moveRowToTopLeftmost(pathFinder.get(pathFinder.get(group)));
    	}
    	groupNumber++;
    }
    
    private ArrayList<Integer> DFS(ArrayList<Integer> pathFinder, int edge){
    	ArrayList<Integer> pathfinder = new ArrayList();
    	ArrayList<Integer> onePlace = new ArrayList();
    	pathfinder=pathFinder;														//remember load
    	boolean reject=false;														//distinguish duplicated node
    	
    	if((pathfinder.size()-1)==edge){
			if((pathfinder.get(pathfinder.size()-1)*dependencyNumber+pathfinder.get(0))==1){
				pathfinder.add(pathfinder.get(0));
				return pathfinder;
			}
		}
    	
    	for(int i=0; i<dependencyNumber; i++){																	//onePlace에 노드에서 갈 수 있는 곳 다 저장
			if(dependencyRelationArray.get((dependencyNumber*i)+pathfinder.get(pathfinder.size()-1))==1){	
				onePlace.add(i);
			}
		}
    	for(int test_2=0; test_2<onePlace.size();test_2++){
    		for(int test=0; test<pathfinder.size(); test++){
				if(pathfinder.get(test)==onePlace.get(test_2)){
					reject=true;
				}
			}
    		if(reject==false){
    		pathfinder.add(onePlace.get(test_2));
    		DFS(pathfinder,edge);
    		}
    		reject=false;
		}

    	return pathfinder;
    }
    
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
