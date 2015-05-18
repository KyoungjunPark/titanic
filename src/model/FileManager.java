package model;

import java.util.ArrayList;

public class FileManager {
	private static FileManager fileManager;
	private FileManager(){}

	private ArrayList<TitanicModel> titanicModelArray;
	private int currentID = -1;
	
	public static FileManager sharedFileManager(){
		if(fileManager == null){
			synchronized (FileManager.class) {
				if(fileManager == null){
					fileManager = new FileManager();
				}
			}
		}
		return fileManager;
	}
	
	public boolean setTitanicModeID(int id){
		if(this.isExistModel(id)){
			this.currentID = id;
			return true;
		}
		return false;
	}
/*
	public int createCLSXModel() throws CreateException{
		return getNextID();
	}
	public int createCLSXModel(String fileName) throws CreateException{
		// file open logic
		return createCLSXModel();
	}
	public int createCLSXModel(int id) throws CreateException{
		if(!this.existModel(id))
			throw new CreateException("The ID does not exist.");
		// copy logic
		return createCLSXModel();
	}
	*/
	public boolean isExistModel(int id){
		for(int i = 0 ; i < this.titanicModelArray.size() ; i++){
			if(this.titanicModelArray.get(i).getID() == i)
				return true;
		}
		return false;
	}
}
