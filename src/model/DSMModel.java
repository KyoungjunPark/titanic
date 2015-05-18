package model;

import java.util.ArrayList;


class DSMModel{

	private int dependencyNumber;
	private ArrayList<Integer> dependencyRelationArray;
	private ArrayList<String> elementsNameArray;
	private String fileURL;
	
	public DSMModel(){}
	public DSMModel(int dependencyNumber, 
			ArrayList<Integer> dependencyRelationArray, 
			ArrayList<String> elementsNameArray,
			String fileURL){
		this.dependencyNumber = dependencyNumber;
		this.dependencyRelationArray = dependencyRelationArray;
		this.elementsNameArray = elementsNameArray;
		this.fileURL = fileURL;
	}
	
	public String toString(){
		String result = "";
		result += this.dependencyNumber+"\n";
		for( int i = 0; i < this.dependencyRelationArray.size() ; i++){
			result += this.dependencyRelationArray.get(i);
			if( i + 1 % this.dependencyNumber == 0)
				result += '\n';
			else
				result += ' ';
		}
		result += '\n';
		for( int i = 0 ; i < this.elementsNameArray.size() ; i++){
			result += this.elementsNameArray.get(i) + '\n';
		}
		return result;
	}
	
}
