package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class DSMModel implements FileReadWrite{

	private int dependencyNumber;
	private int[][] dependencyRelation;
	private String[] elementsName;
	
	public DSMModel(){}
	public DSMModel(int dependencyNumber, int[][] dependencyRelation, String[] elementsName){
		this.dependencyNumber = dependencyNumber;
		this.dependencyRelation = dependencyRelation;
		this.elementsName = elementsName;
	}
	
	@Override
	public void readFile(String fileURL) {
	
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileURL));
			dependencyNumber = Integer.parseInt(input.readLine());
			dependencyRelation = new int[dependencyNumber][dependencyNumber];
			
			//read array of dependency
			for(int i = 0 ; i<dependencyNumber ; i++){
				for(int j = 0 ; j<dependencyNumber ; j++){
					dependencyRelation[i][j] = input.read();
					input.read();
					
				}
				
			}
			
			//read array's elements name
			for(int i = 0 ; i<dependencyNumber ; i++){
				elementsName[i] = input.readLine();
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
