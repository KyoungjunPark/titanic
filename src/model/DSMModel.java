package model;

import org.w3c.dom.NodeList;
import util.GroupNode;
import util.ItemNode;
import util.JSFiles;
import util.Node;

import java.io.*;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;


public class DSMModel extends Model{

	private int dependencyNumber;
	private ArrayList<Integer> dependencyRelationArray = new ArrayList<Integer>();
	private ArrayList<String> elementsNameArray = new ArrayList<String>();

    /**
     * New DSM기능을 위해 추가됨
     * */
    public DSMModel(int nodeSize){
        dependencyNumber = nodeSize;
        for(int i = 0 ; i < nodeSize*nodeSize ; i++){
            dependencyRelationArray.add(0);
        }
        for(int i = 0 ; i <nodeSize ; i++){
            elementsNameArray.add("entity_"+(i+1));
        }
    }
    public DSMModel(File file)throws CreateException, IOException{
		this(JSFiles.getAllString(file));
        this.setFilePath(file.getAbsolutePath());
        this.setFileName(file.getName());
    }
	public DSMModel(int dependencyNumber, 
			ArrayList<Integer> dependencyRelationArray, 
			ArrayList<String> elementsNameArray){
		this.dependencyNumber = dependencyNumber;
		this.dependencyRelationArray = dependencyRelationArray;
		this.elementsNameArray = elementsNameArray;
	}
	public DSMModel(String string) throws CreateException{
		String[] lines = string.split("\r\n");
		this.dependencyNumber = Integer.parseInt(lines[0]);
		//design issue(> or =)
		if(lines.length < this.dependencyNumber * 2 + 1){
			throw new CreateException("DSM Eroerorororo");
		}
		for(int i = 0 ; i < this.dependencyNumber ; i++){
			for(String e : lines[i+1].split(" "))
				this.dependencyRelationArray.add(Integer.parseInt(e));
		}
		for(int i = 0 ; i < this.dependencyNumber ; i++)
			this.elementsNameArray.add(lines[i + 1 + this.dependencyNumber]);
	}
	public void setGroupNode(GroupNode node){
	        this.node = node;
	        /*
	         node data 기준으로 dsm 을 수정할것임.
	         */
	}
	public GroupNode getGroupNode(){
		this.node = new GroupNode("root$");
		for(String name : this.elementsNameArray){
			this.node.addItem(new ItemNode(name));
		}
		return this.node;
	}
    protected ArrayList<ArrayList<String>> getMatrix(CLSXModel clsx){
        ArrayList<ArrayList<String>> matrixList = new ArrayList<ArrayList<String>>();
        if(clsx != null){
            ArrayList<Node> nodeList = clsx.getGroupNode().getAllItemList();
            ArrayList<Integer> relationArray = new ArrayList<Integer>(this.dependencyRelationArray);
            ArrayList<Integer> tempRelationArray = new ArrayList<Integer>(relationArray);
            if(clsx.getGroupNode().isExpanded() == false){
                ArrayList<String> data = new ArrayList<String>();
                data.add(clsx.getGroupNode().getName());
                data.add("1");
                matrixList.add(data);
                return matrixList;
            }
            for( int i = 0 ; i < nodeList.size() ; i++)
                changeRow(relationArray, tempRelationArray, i, this.elementsNameArray.indexOf(nodeList.get(i).getName()));
            tempRelationArray = new ArrayList<Integer>(relationArray);
            for( int i = 0 ; i < nodeList.size() ; i++)
                changeColumn(relationArray, tempRelationArray, i, this.elementsNameArray.indexOf(nodeList.get(i).getName()));
            for( int i = 0 ; i < nodeList.size() ; i++){
                ArrayList<String> data = new ArrayList<String>();
                data.add(nodeList.get(i).getName());
                for( int j = this.dependencyNumber * i ; j < this.dependencyNumber * (i+1) ; j++) {
                    try {
                        data.add(relationArray.get(j) + "");
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
                matrixList.add(data);
            }
            for(ArrayList<String> arrayList : matrixList){
                while(arrayList.size() > matrixList.size()+1)
                    arrayList.remove(arrayList.size()-1);
            }

            nodeList = clsx.getGroupNode().getAllGroupList();
            for(Node node : nodeList){
                if(((GroupNode)node).isExpanded() == false)
                    this.mergeRowAndColumn(matrixList, (GroupNode)node);
            }
        }else{
            for( int i = 0 ; i < this.elementsNameArray.size() ; i++){
                ArrayList<String> data = new ArrayList<String>();
                data.add(this.elementsNameArray.get(i));
                for( int j = this.dependencyNumber * i ; j < this.dependencyNumber * (i+1) ; j++){
                    data.add(this.dependencyRelationArray.get(j) + "");
                }
                matrixList.add(data);
            }
        }
        return matrixList;
    }
    /**
     * arrayList foo line에 temp bar line 의 데이터를 덮어씁니다.
     * @param arrayList 조작될 arrayList
     * @param temp 조작에 사용할 arrayList
     * @param foo line foo
     * @param bar line bar
     */
    private void changeRow(ArrayList<Integer> arrayList, ArrayList<Integer> temp, int foo, int bar){
        if(foo == -1 || bar == -1) return;
        for( int i = 0 ; i < this.dependencyNumber ; i++){
            arrayList.set(this.dependencyNumber * foo + i, temp.get(this.dependencyNumber * bar + i));
        }
    }

    /**
     * arrayList foo column에 temp bar cloumn 의 데이터를 덮어씁니다.
     * @param arrayList 조작될 arrayList
     * @param temp 조작에 사용할 arrayList
     * @param foo column foo
     * @param bar column bar
     */
    private void changeColumn(ArrayList<Integer> arrayList, ArrayList<Integer> temp, int foo, int bar){
        if(foo == -1 || bar == -1) return;
        for( int i = 0 ; i < this.dependencyNumber ; i++){
            arrayList.set(foo + this.dependencyNumber * i, temp.get(bar + this.dependencyNumber * i));
        }
    }

    /**
     * Group 이 닫혀있을때 해당 라인을 합쳐줍니다.
     * 아이템 1~10 이 있을때, 그룹이 닫혀있다면 해당 데이터들이 merge 된 1개의 아이템으로 보여줍니다.
     * 해당 Method 는 param 으로 받는 arrayList 를 직접 조작합니다.
     * @param arrayList 조작될 ArrayList
     * @param node Expanded 를 알 수 있는 node
     */
    private void mergeRowAndColumn(ArrayList<ArrayList<String>> arrayList, GroupNode node){
        ItemNode firstItem = (ItemNode)node.getAllItemList().get(0);
        ItemNode lastItem = (ItemNode)node.getAllItemList().get(node.getAllItemList().size() - 1);
        int firstIndex=-1, lastIndex=-1;
        for(int i = 0; i < arrayList.size() ; i++){
            ArrayList<String> tempArray = arrayList.get(i);
            if(tempArray.get(0).compareTo(firstItem.getName()) == 0)
                firstIndex = i;
            if(tempArray.get(0).compareTo(lastItem.getName()) == 0)
                lastIndex = i;
        }
        if(firstIndex == -1 || lastIndex == -1) return;
        ArrayList<String> targetArray = arrayList.get(firstIndex);
        targetArray.set(0, node.getName());
        for(int i = lastIndex ; i > firstIndex ; i --){
            ArrayList<String> tempArray = arrayList.get(i);
            for(int j = 1 ; j < targetArray.size() ; j++)
                targetArray.set(j, (Integer.parseInt(targetArray.get(j)) + Integer.parseInt(tempArray.get(j))) > 0 ?"1":"0" );
            arrayList.remove(tempArray);
        }
        firstIndex++;
        lastIndex++;
        for(int i = lastIndex ; i > firstIndex ; i --){
            for(int j = 0 ; j < arrayList.size() ; j++){
                ArrayList<String> tempArray = arrayList.get(j);
                tempArray.set(firstIndex, (Integer.parseInt(tempArray.get(firstIndex)) + Integer.parseInt(tempArray.get(i))) > 0?"1":"0");
                tempArray.remove(i);
            }
        }
    }
    /**
     * element 의 이름을 바꿉니다.
     * element 는 이미 dsm 에 존재하고 있어야 합니다.
     * @param originalName 원본 element 의 이름입니다.
     * @param newName 바꿀 이름입니다.
     */
    public void changeName(String originalName, String newName){
        int offset = this.elementsNameArray.indexOf(originalName);
        if(offset != -1)
            this.elementsNameArray.set(offset, newName);
    }
    
    public void addNode(String newNodeName) {
    	this.dependencyNumber++;
    	this.elementsNameArray.add(newNodeName);
    	for(int i=dependencyNumber-1; i<(dependencyNumber-1)*dependencyNumber; i+=dependencyNumber) {
    		this.dependencyRelationArray.add(i, 0);
    	}
    	for(int i=(dependencyNumber-1)*dependencyNumber; i<dependencyNumber*dependencyNumber; i++) {
    		this.dependencyRelationArray.add(0);
    	}
    	ModelManager.sharedModelManager().getCurrentTitanicModel().
    	syncTreeNode(ModelManager.sharedModelManager().getCurrentTitanicModel().getGroupNode().getTreeNode());
    	System.out.println("dependencyNumber");
    	for(int i=0; i<dependencyNumber*dependencyNumber; i++) {
    		if(i%dependencyNumber==0)
    			System.out.println("\n");
    		System.out.print(dependencyRelationArray.get(i));
    	}
    }

    /**
     * 해당 DSMModel 을 dsm 양식에 맞추어 만들어줍니다.
     * @return dsm 양식에 맞는 string 형태
     */
    public String toString(){
        String result = ""+this.dependencyNumber;
        for( int i = 0; i < this.dependencyRelationArray.size() ; i++){
            if( i % this.dependencyNumber == 0)
                result += "\r\n";
            else
                result += ' ';
            result += this.dependencyRelationArray.get(i);
        }
        result += "\r\n";
        result += String.join("\r\n", this.elementsNameArray);
        return result;
    }
}
