package model;

import java.util.ArrayList;

public class ModelManager {
	private static ModelManager modelManager;
	private ModelManager(){}

	private ArrayList<TitanicModel> titanicModelArray;
	private int currentID = -1;
	
	public static ModelManager sharedFileManager(){
		if(modelManager == null){
			synchronized (ModelManager.class) {
				if(modelManager == null){
					modelManager = new ModelManager();
				}
			}
		}
		return modelManager;
	}
	public boolean setTitanicModeID(int id){
		if(this.isExistModel(id)){
			this.currentID = id;
			return true;
		}
		return false;
	}
	public boolean isExistModel(int id){
		for(int i = 0 ; i < this.titanicModelArray.size() ; i++){
			if(this.titanicModelArray.get(i).getID() == i)
				return true;
		}
		return false;
	}
}
