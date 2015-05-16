package Model;

public class FileManager {
	private static FileManager fileManager;
	private FileManager(){}
	private int nextID = 1;
	private int currentID = 0;
	
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
	
	public boolean setCLSXModel(int id){
		if(this.existModel(id)){
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
	public boolean existModel(int id){
		return true;
	}
	/* setter, getter */
	private int getNextID(){
		return this.nextID++;
	}
}
