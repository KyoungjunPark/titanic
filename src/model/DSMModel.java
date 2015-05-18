package model;

import java.util.ArrayList;


public class DSMModel{

	private int dependencyNumber;
	private ArrayList<Integer> dependencyRelationArray;
	private ArrayList<String> elementsNameArray;
	private String fileURL;
	
	public DSMModel(){
		this.dependencyRelationArray = new ArrayList<Integer>();
		this.elementsNameArray = new ArrayList<String>();
	}
	public DSMModel(int dependencyNumber, 
			ArrayList<Integer> dependencyRelationArray, 
			ArrayList<String> elementsNameArray,
			String fileURL){
		this();
		this.dependencyNumber = dependencyNumber;
		this.dependencyRelationArray = dependencyRelationArray;
		this.elementsNameArray = elementsNameArray;
		this.fileURL = fileURL;
	}
	public DSMModel(String string) throws CreateException{
		this();
		String[] lines = string.split("\n");
		this.dependencyNumber = Integer.parseInt(lines[0]);
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
}
