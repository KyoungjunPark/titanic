package model;

import util.GreenTreeNode;
import util.GroupNode;
import util.JSFiles;

import java.io.File;
import java.util.ArrayList;

public class TitanicModel {

	private static int nextID = 0;
	private int id;
	private DSMModel dsmModel = null;
	private CLSXModel clsxModel = null;
	
	public TitanicModel(){
		this.id = TitanicModel.nextID();
	}

    /**
     * TitanicModel 을 save 합니다. ( DSM, CLSX )
     * 만약 filePath 가 null 일 경우 save 에 실패합니다.
     * 각자의 모델을 Save 하고 싶을경우, 각 모델에 save method 를 참고하세요. {@link model.DSMModel} {@link model.CLSXModel}
     * save 에 실패할경우 SaveException 을 발생시킵니다.
     * @throws SaveException
     */
    public void save()throws SaveException{
        this.dsmModel.save();
        if(this.clsxModel != null)
            this.clsxModel.save();
    }

	protected void setDsmModel(DSMModel dsmModel){
        /**
         * Titanic의 dsm model 을 set 합니다.
         * overwrite 하기 때문에 기존의 dsm 포인터는 사라집니다.
         *
         * @param {@link model.DSMModel}
         */
        this.dsmModel = dsmModel;
    }
    protected void setClsxModel(CLSXModel clsxModel){
        /**
         * Titanic의 clsxmodel 을 set 합니다.
         * overwrite 하기 때문에 기존의 clsx 포인터는 사라집니다.
         *
         * @param {@link model.CLSXModel}
         */
        this.clsxModel = clsxModel;
    }
	protected void setClsxModel(File file)throws CreateException{
		if(JSFiles.getFileExtension(file).toLowerCase().compareTo(".clsx") ==0) {
			CLSXModel clsx = new CLSXModel(file);
			this.setClsxModel(clsx);
		}
	}
    public DSMModel getDsmModel(){ return this.dsmModel; }
    public CLSXModel getClsxModel(){ return this.clsxModel; }
	public GroupNode getGroupNode(){
		if(this.clsxModel != null){
			return this.clsxModel.getGroupNode();
		}
		return this.dsmModel.getGroupNode();
	}
	public int getID(){
		return this.id;
	}

	static private int nextID(){
		return TitanicModel.nextID++;
	}
    public ArrayList<ArrayList<String>> getMatrixData(){
        return this.dsmModel.getMatrix(this.clsxModel);
    }

    /**
     * root 의 트리 노드를 받아 model 과 sync 합니다.
     * 가장 최상의 root 를 받아야 합니다.
     * 가지고 있는 데이터의 갯수와 node 의 갯수가 다를경우 sync 가 실패합니다.
     * 트리노드는 다음을 참고해주세요. {@link GreenTreeNode}
     * @param root
     * @return 성공 여부를 알려줍니다.
     */
    public boolean syncTreeNode(GreenTreeNode root){
        try{
            if(this.clsxModel == null){
                this.setClsxModel(new CLSXModel(root));
            }else{
                this.clsxModel.setTreeNode(root);
                System.out.println(this.clsxModel.getGroupNode().getGroupData());
            }
        }catch (CreateException e){
            return false;
        }
        return true;
    }
    public boolean isEdit(){
    	return true;
        //return this.dsmModel.isEdit() || this.clsxModel.isEdit();
    }

    /**
     * clsxmodel 을 제거합니다.
     * New ClsxModel 등에 이용합니다.
     */
    public void removeClsxModel(){
        this.clsxModel = null;
    }
}
