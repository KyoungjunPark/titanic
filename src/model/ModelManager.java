package model;

import java.io.File;
import java.util.ArrayList;

public class ModelManager {
	private static ModelManager modelManager;
	private ModelManager(){}

	private ArrayList<TitanicModel> titanicModelArray;
	private int currentID = -1;
	
	public static ModelManager sharedModelManager(){
		/**
		 * ModelManager 의 Singletone 함수 입니다.
		 * ModelManger 가 1개만 생성됨을 보장합니다.
		 *
		 * @return {@link ModelManager}
		 */
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
			this.currentID = id;
			return true;
		}
		return false;
	}

	public int createTitanicModel(File file)throws CreateException{
		/**
		 * file object 파일을 받아 TitanicModel 을 만듭니다.
		 * file 의 확장자 에 따라 DSM, CLSX 를 구분합니다.
		 *
		 * @param File file 생성된 파일 오브젝트를 받습니다.
		 * @return 생성된 titanic의 id 를 리턴합니다.
		 * @exception CreateException 파일의 확장자, 형식등이 맞지 않을경우 발생합니다. 자세한 사항은 메시지를 통해 전달합니다.
		 */
		return -1;
	}

	public boolean isExistModel(int id){
		for(int i = 0 ; i < this.titanicModelArray.size() ; i++){
			if(this.titanicModelArray.get(i).getID() == id)
				return true;
		}
		return false;
	}
}
