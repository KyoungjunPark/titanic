package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


class DSMModel implements FileReadWrite{

	private int dependencyNumber;
	private int[][] dependencyRelation;
	private String[] elementsName;
	private String fileURL;
	
	public DSMModel(){}
	public DSMModel(int dependencyNumber, int[][] dependencyRelation, String[] elementsName, String fileURL){
		this.dependencyNumber = dependencyNumber;
		this.dependencyRelation = dependencyRelation;
		this.elementsName = elementsName;
		this.fileURL = fileURL;
	}
	
	@Override
	public void readFile(String fileURL)
	{
	
		this.fileURL = fileURL;
		try {
			BufferedReader input = connectReadFile(fileURL);
			dependencyNumber = Integer.parseInt(input.readLine());
			
			readArrayInformation(input);
			
			readElementsName(input);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private BufferedReader connectReadFile(String fileURL) throws FileNotFoundException
	{
		return new BufferedReader(new FileReader(fileURL));
		
	}
	private void readArrayInformation(BufferedReader input) throws IOException
	{
		dependencyRelation = new int[dependencyNumber][dependencyNumber];
		
		//read array of dependency
		for(int i = 0 ; i<dependencyNumber ; i++){
			for(int j = 0 ; j<dependencyNumber ; j++){
				dependencyRelation[i][j] = input.read()-'0';
				input.read();	
			}
			input.readLine();
		}
	}
	private void readElementsName(BufferedReader input) throws IOException
	{
		elementsName = new String[dependencyNumber];
		//read array's elements name
		for(int i = 0 ; i<dependencyNumber ; i++){
			elementsName[i] = input.readLine();
		}
		
	}
	@Override
	public void writeFile(String fileURL) {
		try {
			PrintWriter output = connetWriteFile(fileURL);
			
			output.println(dependencyNumber);
			
			writeArrayInformation(output);
			
			writeElementsName(output);
			
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	private PrintWriter connetWriteFile(String fileURL) throws IOException
	{
		return new PrintWriter(fileURL);
	}
	private void writeArrayInformation(PrintWriter output)
	{
		for(int i = 0 ; i< dependencyNumber ; i++){
			for(int j = 0 ; i< dependencyNumber ; j++){
				output.write(dependencyRelation[i][j]);
				output.write(" ");
			}
			output.write("\n");
		}
		
	}
	private void writeElementsName(PrintWriter output)
	{
		for(int i = 0 ; i<dependencyNumber ; i++){
			output.println(elementsName[i]);
		}
		
	}
	
	public int getDependencyNumber() {
		return dependencyNumber;
	}
	public int[][] getDependencyRelation() {
		return dependencyRelation;
	}
	public String[] getElementsName() {
		return elementsName;
	}
	public String getFileURL() {
		return fileURL;
	}

	
	
}
