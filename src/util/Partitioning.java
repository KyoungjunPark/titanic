package util;

import model.CreateException;

import java.util.ArrayList;
import java.util.Collections;

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
        
        printTest();
        doPartitioning();
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
        for(int i = 1 ; i < list.size() ; i++){
            list = recurNthSquare(list);
        } 
        for(int i = 0 ; i<dependencyNumber ; i++){
        	diagonal.add(list.get(i*dependencyNumber+i));
        }
        return diagonal;
    }
    private ArrayList<Integer> recurNthSquare(ArrayList<Integer> list){
        ArrayList<Integer> result = new ArrayList<Integer>();
       
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
    			pathFinder=DFS(pathFinder, edge);
    				if(pathFinder.size()==edge+1){
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
    	pathfinder=pathFinder;														//remember load
    	boolean reject=false;														//distinguish duplicated node
    	
    	for(int i=0; i<dependencyNumber; i++){
			if(dependencyRelationArray.get((dependencyNumber*i)+pathfinder.get(pathfinder.size()-1))==1){
				if((pathfinder.size()-1)!=edge){
					for(int test=0; test<pathfinder.size(); test++){
						if(pathfinder.get(test)==i){
							reject=true;
						}
					}
				}
				else if((pathfinder.size()-1)==edge){
					if(pathfinder.get(0)*dependencyNumber+(pathfinder.get(pathfinder.size()-1))==1){
						pathfinder.add(pathfinder.get(0));
						break;
					}
				}
				if(reject==false){
					DFS(pathfinder, edge);
				}
			}
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
