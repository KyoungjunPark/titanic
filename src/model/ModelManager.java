package model;

import util.JSFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ModelManager {
	private volatile static ModelManager modelManager;
	private ModelManager(){}

	private ArrayList<TitanicModel> titanicModelArray = new ArrayList<TitanicModel>();
	private int currentID = -1;

    /**
     * ModelManager 의 Singletone 함수 입니다.
     * ModelManger 가 1개만 생성됨을 보장합니다.
     *
     * @return {@link ModelManager}
     */
	public static ModelManager sharedModelManager(){
		if(modelManager == null){
			synchronized (ModelManager.class) {
				if(modelManager == null){
					modelManager = new ModelManager();
				}
			}
		}
		return modelManager;
	}
	public boolean setTitanicModelID(int id){
		if(this.isExistModel(id)){
			this.setCurrentID(id);
			return true;
		}
		return false;
	}

    /**
     * file object 파일을 받아 TitanicModel 을 만듭니다.
     * file 의 확장자 에 따라 DSM, CLSX 를 구분합니다.
     *
     * @param file 생성된 파일 오브젝트를 받습니다.
     * @return 생성된 titanic의 id 를 리턴합니다.
     * @throws CreateException 파일의 확장자, 형식등이 맞지 않을경우 발생합니다. 자세한 사항은 메시지를 통해 전달합니다.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	public int createTitanicModel(File file)throws CreateException{
        String extension = JSFiles.getFileExtension(file).toLowerCase();
        
        TitanicModel model = new TitanicModel();
        
        if(extension.equals(".dsm")){
            DSMModel dsm = null;
			try {
				dsm = new DSMModel(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.setDsmModel(dsm);
            
        }else if(extension.equals(".clsx")){
            CLSXModel clsx = new CLSXModel(file);
            model.setClsxModel(clsx);
        }else{
        	JOptionPane.showMessageDialog(null, extension+"file format is not accepted");
            throw new CreateException("지원하지 않는 확장자입니다.");
        }
        this.addTitanicModel(model);
		return model.getID();
	}

	public boolean isExistModel(int id){
		for(int i = 0 ; i < this.titanicModelArray.size() ; i++){
			if(this.titanicModelArray.get(i).getID() == id)
				return true;
		}
		return false;
	}
    private void addTitanicModel(TitanicModel model){
        this.titanicModelArray.add(model);
    }
    private void setCurrentID(int id){
        this.currentID = id;
    }
    public int getCurrentID(){
    	return currentID;
    }

    /**
     * Titanic model 을 저장합니다.
     * @throws SaveException 저장에 실패할 경우에 Exception 을 발생합니다. filePath 설정 권한등의 이유가 있습니다. {@link model.SaveException}
     */
    public void save()throws SaveException{

    }
}
