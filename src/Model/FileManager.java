package Model;

public class FileManager {
	private static FileManager fileManager;
	private FileManager(){}
	private int next_id = 1;
	private int current_id = 0;
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
			this.current_id = id;
			return true;
		}
		return false;
	}

	public int createCLSXModel(){
		return getNextID();
	}
	public int createCLSXModel(String fileName){
		return createCLSXModel();
	}
	public int createCLSXModel(int id){
		return createCLSXModel();
	}
	public boolean existModel(int id){
		return true;
	}
	/* setter, getter */
	private int getNextID(){
		return this.next_id++;
	}
}
