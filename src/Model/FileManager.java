package Model;

public class FileManager {
	private static FileManager fileManager;
	private FileManager(){}
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
		
		return true;
	}
}
