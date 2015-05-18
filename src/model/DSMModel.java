package model;

import java.util.ArrayList;


class DSMModel{

	private int dependencyNumber;
	private ArrayList<Integer> dependencyRelationArray;
	private ArrayList<String> elementsNameArray;
	private String fileURL;
	
	public DSMModel(int dependencyNumber, 
			ArrayList<Integer> dependencyRelationArray, 
			ArrayList<String> elementsNameArray,
			String fileURL){
		this.dependencyNumber = dependencyNumber;
		this.dependencyRelationArray = dependencyRelationArray;
		this.elementsNameArray = elementsNameArray;
		this.fileURL = fileURL;
	}
	public DSMModel(String string) throws CreateException{
		String[] lines = string.split("\n");
		this.dependencyNumber = Integer.parseInt(lines[0]);
		if(lines.length < this.dependencyNumber * 2 + 1){
			throw new CreateException("DSM 데이터 형식이 옳바르지 않습니다.");
		}
		for(int i = 0 ; i < this.dependencyNumber ; i++){
			for(String e : lines[i+1].split(" "))
				this.dependencyRelationArray.add(Integer.parseInt(e));
		}
		for(int i = 0 ; i < this.dependencyNumber ; i++)
			this.elementsNameArray.add(lines[i + 1 + this.dependencyNumber]);
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
