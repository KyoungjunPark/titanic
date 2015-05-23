package model;

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
            
        }else{
            ArrayList<String> data = new ArrayList<String>();
            for( int i = 0 ; i < this.elementsNameArray.size() ; i++){
                data.add(this.elementsNameArray.get(i));
                for( int j = this.dependencyNumber * i ; j < this.dependencyNumber * (i+1) ; j++){
                    data.add(this.dependencyRelationArray.get(i) + "");
                }
            }
        }
        return matrixList;
    }
}
