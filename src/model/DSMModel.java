package model;

import org.w3c.dom.NodeList;
import util.GroupNode;
import util.ItemNode;
import util.JSFiles;
import util.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class DSMModel extends Model{

	private int dependencyNumber;
	private ArrayList<Integer> dependencyRelationArray = new ArrayList<Integer>();
	private ArrayList<String> elementsNameArray = new ArrayList<String>();

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
	public GroupNode getGroupNode(){
		this.node = new GroupNode("root$");
		for(String name : this.elementsNameArray){
			this.node.addItem(new ItemNode(name));
		}
		return this.node;
	}
	public String toString(){
		String result = ""+this.dependencyNumber;
		for( int i = 0; i < this.dependencyRelationArray.size() ; i++){
			if( i % this.dependencyNumber == 0)
				result += '\n';
			else
				result += ' ';
			result += this.dependencyRelationArray.get(i);
		}
		result += '\n';
        result += String.join("\n", this.elementsNameArray);
		return result;
	}
    protected ArrayList<ArrayList<String>> getMatrix(CLSXModel clsx){
        ArrayList<ArrayList<String>> matrixList = new ArrayList<ArrayList<String>>();
        if(clsx != null){
            ArrayList<Node> nodeList = clsx.getGroupNode().getItemList();
            ArrayList<Integer> relationArray = new ArrayList<Integer>(this.dependencyRelationArray);
            for( int i = 0 ; i < nodeList.size() ; i++){
                String name = nodeList.get(i).getName();
                changeColumn(relationArray, i, this.elementsNameArray.indexOf(name));
            }
            for( int i = 0 ; i < nodeList.size() ; i++){
                ArrayList<String> data = new ArrayList<String>();
                data.add(nodeList.get(i).getName());
                for( int j = this.dependencyNumber * i ; j < this.dependencyNumber * (i+1) ; j++){
                    data.add(relationArray.get(i) + "");
                }
                matrixList.add(data);
            }
            for(ArrayList<String> arrayList : matrixList){
                while(arrayList.size() > matrixList.size()+1)
                    arrayList.remove(arrayList.size()-1);
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
        System.out.println(matrixList);
        return matrixList;
    }
    private void changeColumn(ArrayList<Integer> arrayList, int foo, int bar){
        ArrayList<Integer> temp = new ArrayList(arrayList);
        for( int i = 0 ; i < this.dependencyNumber ; i++){
            arrayList.set(this.dependencyNumber * foo + i, temp.get(this.dependencyNumber * bar + i));
            arrayList.set(this.dependencyNumber * bar + i, temp.get(this.dependencyNumber * foo + i));
        }
        temp = new ArrayList<>(arrayList);
        for( int i = 0 ; i < this.dependencyNumber ; i++){
            arrayList.set(foo + this.dependencyNumber * i, temp.get(bar + this.dependencyNumber * i));
            arrayList.set(bar + this.dependencyNumber * i, temp.get(foo + this.dependencyNumber * i));
        }
    }
}
