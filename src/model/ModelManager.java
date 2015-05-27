package model;

import util.JSFiles;
import util.TreeNode;

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
     * ModelManager 가 1개만 생성됨을 보장합니다.
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
		EventManager.callEvent("before-open");
        String extension = JSFiles.getFileExtension(file).toLowerCase();
		TitanicModel model =  new TitanicModel();
        if(extension.compareTo(".dsm") == 0){
			DSMModel dsm = null;
			try {
				dsm = new DSMModel(file);
			} catch (IOException e) {
				throw new CreateException("IO Error");
			}
			model.setDsmModel(dsm);
			this.addTitanicModel(model);
        }else{
        	JOptionPane.showMessageDialog(null, extension + "file format is not accepted");
            throw new CreateException("지원하지 않는 확장자입니다.");
        }
		return model.getID();
	}
	public void setClsx(File file)throws CreateException{
        EventManager.callEvent("before-open");
		String extension = JSFiles.getFileExtension(file).toLowerCase();
		TitanicModel model = this.getCurrentTitanicModel();
		if(model == null)
			throw new CreateException("You have to set id");
		if(extension.compareTo(".clsx") == 0){
			model.setClsxModel(file);
		}else{
			JOptionPane.showMessageDialog(null, extension + "file format is not accepted");
			throw new CreateException("지원하지 않는 확장자입니다.");
		}
	}
	/**
	 * id 값을 기준으로 현재 있는지 체크합니다.
	 * @param id integer 의 id 로 비교합니다.
	 * @return 존재할경우 true 없을경우 false 입니다.
	 */
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
    public void setCurrentID(int id){
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
        EventManager.callEvent("before-save");
        EventManager.callEvent("after-save");
    }

	/**
	 * 현제 set 되어있는 {@link TitanicModel} 을 돌려줍니다.
	 * 다음과 같은 경우에는 null 이반환됩니다.
	 * >> CLSX 파일이 설정되어 있지 않을경우
	 * >> 현재 set되어 있는 id의 object 가 없을경우 ( 비정상적 )
	 * >> 그외는 제보바랍니다.
	 * @return {@link TitanicModel}
	 */
	public TitanicModel getCurrentTitanicModel(){
		return this.getTitanicMode(this.getCurrentID());
	}

	/**
	 * node 를 받아 tiatanic model 을 duplicate 합니다.
	 * model 의 id 가 존재하는지 확인하세요.
	 * @param id {@link TitanicModel} 의 id 입니다. 해당 model 의 dsm 데이터를 복사해 가져옵니다.
	 * @param node {@link TreeNode} 그룹 정보입니다. 해당 그룹정보를 가지고 duplicate 합니다.
	 * @return 새로 생성된 tiatanic의 id 를 반화합니다.
	 */
	public int duplicateTitanicModel(int id, TreeNode node){
		return -1;
	}

	/**
	 * node 를 받아 tiatanic model 을 edit 합니다.
	 * model 의 id 가 존재하는지 확인하세요.
	 * @param id {@link TitanicModel} 의 id 입니다. 해당 model 의 dsm 데이터를 공유합니다.
	 * @param node {@link TreeNode} 그룹 정보입니다. 해당 그룹정보를 가지고 edit 합니다.
	 * @return 새로 생성된 tiatanic의 id 를 반화합니다.
	 */
	public int editTatanicModel(int id, TreeNode node){
		return -1;
	}
    /**
     * id 에 해당하는 Titanic 모델을 받습니다.
     * @param id
     * @return
     */
    private TitanicModel getTitanicMode(int id){
        for(TitanicModel model : this.titanicModelArray){
            if(model.getID() == id)
                return model;
        }
        return null;
    }
    /**
     * 현재 저장중인 TitanicModel 의 갯수를 반환합니다.
     * @return integer
     */
    public int getTitanicModelCount(){
        return this.titanicModelArray.size();
    }

    public void removeTitanicModel(int id){
        TitanicModel model = this.getTitanicMode(id);
        if(model != null){
            this.titanicModelArray.remove(model);
            model = null;
        }
    }
}
