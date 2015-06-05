package util;

import model.CreateException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.DoubleSummaryStatistics;

public class Partitioning {

    private int dependencyNumber;
    private int originDependencyNumber;

    private ArrayList<Integer> dependencyRelationArray;
    private ArrayList<Integer> originDependencyRelationArray;

    private ArrayList<String> elementsNameArray;
    private ArrayList<String> originElementsNameArray;
    
    private ArrayList<ArrayList<String>> groupList;
    
    private int removeRow;
    private int removeColumn;
    private int proRow;
    private int preColumn;
    private int groupNumber;
    
    private boolean find;
    public Partitioning(int originDependencyNumber, ArrayList<Integer> originDependencyRelationArray, ArrayList<String> originElementsNameArray,
    		int dependencyNumber, ArrayList<Integer> dependencyRelationArray, ArrayList<String> elementsNameArray){
        this.originDependencyNumber = originDependencyNumber;
        this.originDependencyRelationArray = originDependencyRelationArray;
        this.originElementsNameArray = originElementsNameArray;

        this.dependencyNumber = dependencyNumber;
        this.dependencyRelationArray = dependencyRelationArray;
        this.elementsNameArray = elementsNameArray;
        
        this.proRow=0;
        this.groupNumber=0;
        this.removeRow=0;
        this.removeColumn=0;
        this.preColumn=0;
        this.find=false;
        
        this.groupList = new ArrayList<ArrayList<String>>();
        doPartitioning();
		System.out.println(groupList);
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
        
        quickTriangleAlgorithm();

    }
    
    private boolean checkRow(){													
    	boolean changeRow = false;												
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
    
    private boolean checkColumn(){												
    	boolean changeColumn=false;												
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
        //if(row == dependencyNumber -1) throw new CreateException("Impossible input");
        //////////////////////////////////////////////////////////////////////////////////////
        for(int i = 0 ; i <originDependencyNumber; i++){
        Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, originDependencyNumber*(originDependencyNumber-1-preColumn)+i);
    	}
        for(int i = 0 ; i <originDependencyNumber; i++){
        Collections.swap(originDependencyRelationArray, i*originDependencyNumber+(row+removeRow), (i+1)*(originDependencyNumber)-preColumn-1);
        }
		Collections.swap(originElementsNameArray, row+removeRow, removeRow+dependencyNumber-1-preColumn);
		//////////////////////////////////////////////////////////////////////////////////////
		for(int i = 0 ; i <dependencyNumber; i++){
	        Collections.swap(dependencyRelationArray, row*dependencyNumber+i, dependencyNumber*(dependencyNumber-1-preColumn)+i);	//row
	    }
	    for(int i = 0 ; i <dependencyNumber; i++){
	        Collections.swap(dependencyRelationArray, i*dependencyNumber+row, (i*dependencyNumber)+dependencyNumber-preColumn-1);	//column
	    }
		Collections.swap(elementsNameArray, row, dependencyNumber-1-preColumn);
		preColumn++;
    }
    private void moveRowToBottomRightmost(int row){
    	
    	ArrayList<String> group = new ArrayList<String>();
    	group.add(elementsNameArray.get(row));
    	groupList.add(group);
    	////////////////////////////////////////////////////////////////////
    	for(int i = 0 ; i <originDependencyNumber; i++){
            Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, originDependencyNumber*(originDependencyNumber-1)+i);
    	}
        for(int i = 0 ; i <originDependencyNumber; i++){    
            Collections.swap(originDependencyRelationArray, i*originDependencyNumber+(row+removeRow), (i+1)*(originDependencyNumber)-1);
        }
    	Collections.swap(originElementsNameArray, row+removeRow, removeRow+dependencyNumber-1);
   	 	////////////////////////////////////////////////////////////////////
        //dependencyRelationArray's row to bottom or right
        for(int i = 0 ; i <dependencyNumber; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, (dependencyNumber-1)*dependencyNumber+i);
        }
        for(int i = 0 ; i <dependencyNumber; i++){
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
    	ArrayList<String> group = new ArrayList<String>();
    	group.add(elementsNameArray.get(row));
    	groupList.add(group);
    	
    	 for(int i = 0 ; i <originDependencyNumber; i++){
             Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, (removeRow*originDependencyNumber)+i);//row
    	 }
         for(int i = 0 ; i <originDependencyNumber; i++){
             Collections.swap(originDependencyRelationArray, i*originDependencyNumber+(row+removeRow), i*originDependencyNumber+removeRow);//column
         }
    	 Collections.swap(originElementsNameArray, row+removeRow, removeRow);
    	 
    	//dependencyRelationArray's row to top or left
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, row*dependencyNumber+i, i);
        }
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, i*dependencyNumber+row, i*dependencyNumber);
        }
      //elementsNameArray's row to top or left
        Collections.swap(elementsNameArray, row, 0);
        
        for(int i=0 ; i<dependencyNumber ; i++){
        	dependencyRelationArray.remove(i * dependencyNumber-i);
        }
        for(int i=0 ; i<dependencyNumber-1 ; i++){
        	dependencyRelationArray.remove(0);
        }
        
        elementsNameArray.remove(0);
        dependencyNumber--;
        removeRow++;
    }
    
    private void groupMoveToTop(int row){
    	
    	
    	for(int i = 0 ; i <originDependencyNumber; i++){
    		
            Collections.swap(originDependencyRelationArray, (row+removeRow)*originDependencyNumber+i, ((removeRow+proRow)*originDependencyNumber)+i);
    	}
    	for(int i = 0 ; i <originDependencyNumber; i++){
            Collections.swap(originDependencyRelationArray, i*originDependencyNumber+(row+removeRow), i*originDependencyNumber+(proRow+removeRow));
        }
   	 	Collections.swap(originElementsNameArray, row+removeRow, removeRow+proRow);
   	 	////////////////////////////////////////////////////////////////////////
   	 	//dependencyRelationArray's row to top or left
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, row * dependencyNumber + i, proRow * dependencyNumber+i);					//row
        }
        for(int i = 0 ; i <dependencyNumber ; i++){
            Collections.swap(dependencyRelationArray, i * dependencyNumber + row, i * dependencyNumber+proRow);	//column
        }
        //elementsNameArray's row to top or left
        Collections.swap(elementsNameArray, row, proRow);
   	 	////////////////////////////////////////////////////////////////////////
   	 	proRow++;
    }
    private void afterGroupMove(int row){
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
    	 ArrayList<Integer> result = new ArrayList<Integer>();
    	 
    	if(list.size() <= 0) throw new CreateException("Impossible input");

    	result = list;
        for(int i = 1 ; i < dependencyNumber ; i++){
            result = recurNthSquare(result, list);
        }
        for(int i = 0 ; i<dependencyNumber ; i++){
        	diagonal.add(result.get(i*dependencyNumber+i));
        }
        return diagonal;
    }
    private ArrayList<Integer> recurNthSquare(ArrayList<Integer> addResult, ArrayList<Integer> list){
        ArrayList<Integer> result = new ArrayList<Integer>();
       
        for(int i = 0 ; i < dependencyNumber ; i++){
            for(int j = 0 ; j < dependencyNumber ; j++){
                //(i,j)
                int pointSum = 0;
                for(int k = 0 ; k <  dependencyNumber; k++){
                    pointSum += addResult.get(i*dependencyNumber + k)* list.get(j + dependencyNumber*k);
                }
                result.add(pointSum);
            }
        }
        return result;
    }
    
    private void findLoop(ArrayList<Integer> diagonal) throws CreateException{
    	ArrayList<Integer> starting = new ArrayList<Integer>();				//save a starting point of DFS
    	ArrayList<Integer> pathFinder = new ArrayList<Integer>();			//save path
    	ArrayList<String> group = new ArrayList<String>();
    	boolean end=false;
    	for(int i=0; i<diagonal.size();i++){
    		if(diagonal.get(i)==0){
				moveDownORRight(i);
    		}
    	}
    	
    	preColumn=0;
    	for(int i=0; i<diagonal.size();i++){
    		if(diagonal.get(i)!=0){
    			starting.add(i);
    		}
    	}
    	
    	/*if(starting.size()==0){
    		group.add(elementsNameArray.get(0));
    		groupList.add(group);
    		
    		for(int i=0 ; i<dependencyNumber ; i++){
            	dependencyRelationArray.remove(i * dependencyNumber-i);
            }
            for(int i=0 ; i<dependencyNumber-1 ; i++){
            	dependencyRelationArray.remove(0);
            }
            
            elementsNameArray.remove(0);
            dependencyNumber--;
            removeRow++;
            
    		return ;
    	}*/
    		
    	for(int edge = dependencyNumber; edge>1 ; edge--){
    		
    		pathFinder.add(starting.get(0));
    			if(DFS(pathFinder, edge).size()==edge){
    				end=true;
    				break;
   				}
    		if(end==false){
    		pathFinder.clear();
    		}
    	}
    		find=false;
    		
    		for(int gr=0; gr < pathFinder.size(); gr++){
    			group.add(elementsNameArray.get(pathFinder.get(gr)));
        	}
    		

    		for(int i=0; i < group.size(); i++){
    			for(int j=0; j<elementsNameArray.size();j++){
    				if(elementsNameArray.get(j).compareTo(group.get(i)) == 0){
    					groupMoveToTop(j);
    					
    					break;
    				}
    			}
    		}
    		for(int i=0; i < group.size(); i++){
    					afterGroupMove(i);
    		}
    		
    		proRow=0;
        	groupList.add(group);
    }
    
    private ArrayList<Integer> DFS(ArrayList<Integer> pathFinder, int edge){
    	ArrayList<Integer> pathfinder = new ArrayList<Integer>();
    	ArrayList<Integer> onePlace = new ArrayList<Integer>();
    	
    	pathfinder=pathFinder;														//remember load
    														//distinguish duplicated node
    	if((pathfinder.size())==edge){
			if(dependencyRelationArray.get(pathfinder.get(0)*dependencyNumber+pathfinder.get(pathfinder.size()-1))==1){
				find=true;
				return pathfinder;
			}
		}
    	
    	for(int i=0; i<dependencyNumber; i++){																	//onePlace에 노드에서 갈 수 있는 곳 다 저장
			if(dependencyRelationArray.get((dependencyNumber*i)+pathfinder.get(pathfinder.size()-1))==1){	
				onePlace.add(i);
			}
		}
    	if(onePlace.size()==0){
    		int noWay = pathfinder.get(pathfinder.size()-1);
    		pathfinder.remove(pathfinder.size()-1);
    		onePlace.remove(onePlace.indexOf(noWay));
    	}
    	for(int test_2=0; test_2<onePlace.size();test_2++){
    		for(int test=0; test<pathfinder.size(); test++){
				if(pathfinder.get(test)==onePlace.get(test_2)){
					onePlace.remove(test_2);
					test_2--;
					break;
				}
			}
    	}
    		for(int i=0; i<onePlace.size();i++){
    			pathfinder.add(onePlace.get(i));
    			DFS(pathfinder,edge);
    			if(find==true){return pathfinder;}
    			pathfinder.remove(pathfinder.size()-1);
    			if(onePlace.size()==0){
    				break;
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
    private void printTest2(){
    System.out.println("originDependencyNumber : " + originDependencyNumber);
    System.out.println("originDependencyRelationArray");
    for(int i = 0 ; i < originDependencyNumber*originDependencyNumber ; i++){
        if(i % originDependencyNumber == 0) System.out.println();
        System.out.print(originDependencyRelationArray.get(i)+" ");
    }
    System.out.println("\noriginElementsNameArray");
    for(String a : originElementsNameArray) System.out.println(a);
    }
    
    private void printGroup(){
    	for(int i=0 ;i<groupList.size();i++){
		for(int j=0;j<groupList.get(i).size();j++){
			System.out.print(groupList.get(i).get(j));
		}
		System.out.println("");
	}
    }
    
    private void quickTriangleAlgorithm(){
    	ArrayList<Integer> pressGroup = new ArrayList<Integer>();			//최종적으로 0과 1이 들어감
    	ArrayList<Integer> nodeNumberInGroup= new ArrayList<Integer>();		//그룹의 노드 개수
    	
    	int[][] zeroOne;													//0과 1 들어가기전에 저장해두는 곳
    	int[] Plus;
    	String[] PlusName;
    	int groupNumber=groupList.size();									//그룹의 개수
    	int row=0, column=0;
    	
    	zeroOne= new int[groupNumber][groupNumber];
    	Plus = new int[groupNumber];
    	PlusName = new String[groupNumber];
    	
    	for(int i=0; i<groupNumber ; i++){
    		for(int j=0; j<groupNumber;j++){
    			zeroOne[i][j]=0;
    		}
    		Plus[i]=0;
    		PlusName[i]=null;
    	}
    		for(int i=0;i<originElementsNameArray.size();i++){
    	    	for(int j=0; j<groupNumber; j++){
    	    			if(groupList.get(j).get(0)==originElementsNameArray.get(i)){
    	    				groupList.add(groupList.get(j));
    	    				groupList.remove(j);
    	    				break;
    	    			}
    	    		}
    		}
    		
    				for(int i=0 ;i<groupList.size();i++){
    						nodeNumberInGroup.add(groupList.get(i).size());
    					}
    						
    						for(int a=0; a < groupNumber ; a++){
    							for(int b=0; b < nodeNumberInGroup.get(a) ; b++){
    								for(int d=0; d < groupNumber ; d++){
    									for(int c=0; c < nodeNumberInGroup.get(d) ; c++){
    										if(originDependencyRelationArray.get(row*originDependencyNumber+column)==1){
    										
    										zeroOne[a][d]=1;
    										}
    										column++;
    									}
    								}
    								column=0;
									row++;
    							}
    						}
    								for(int i=0; i<groupNumber;i++){
    									zeroOne[i][i]=0;
    								}
    										for(int i=0; i<groupNumber; i++){
    											for(int j=0; j<groupNumber; j++){
    												pressGroup.add(zeroOne[i][j]);
    											}
    										}
    		
    		for(int i=0;i<groupNumber;i++){
    			for(int j=0;j<groupNumber;j++){Plus[i]=Plus[i]+zeroOne[i][j];}
    		}
    		for(int i=0;i<groupNumber;i++){
    			PlusName[i]=groupList.get(i).get(0);
    		}
    		
    		sort(Plus,PlusName);
    		printGroup();
            
    		for(int i=0; i<groupNumber; i++){
    				for(int j=0; j<groupNumber; j++){
    					if(PlusName[i]==groupList.get(j).get(0)){
    						groupList.add(groupList.get(j));
    					}
    				}
    		}
    		
    		System.out.println("");
    		System.out.println("");
    		printGroup();
    		for(int i=0; i<groupNumber; i++){
    			groupList.remove(0);
    		}
    		
    	for(int i=0 ;i<groupList.size();i++){
   			for(int j=0;j<groupList.get(i).size();j++){
   				moveToTop(originElementsNameArray.indexOf(groupList.get(i).get(j)));
   			}
    	}
    }
    private void sort(int[] Plus, String[] PlusName){
    	int min;
    	String temp;
    	for(int j=0;j<groupNumber;j++){
    		for(int i=0; i<groupNumber-1; i++){
    			if(Plus[i]>Plus[i+1]){
    				min=Plus[i+1];
    				Plus[i+1]=Plus[i];
    				Plus[i]=min;
    				temp=PlusName[i+1];
    				PlusName[i+1]=PlusName[i];
    				PlusName[i]=temp;
    			}
    		}
    	}
    }
    private void moveToTop(int row){
    	
    	
    	for(int i = 0 ; i <originDependencyNumber; i++){
    		
            Collections.swap(originDependencyRelationArray, row*originDependencyNumber+i, (proRow*originDependencyNumber)+i);
    	}
    	for(int i = 0 ; i <originDependencyNumber; i++){
            Collections.swap(originDependencyRelationArray, i*originDependencyNumber+row, i*originDependencyNumber+proRow);
        }
   	 	Collections.swap(originElementsNameArray, row, proRow);
   	 	
   	 	proRow++;
    }
}
