package model;

import util.GroupNode;

import java.security.acl.Group;

class TitanicModel {
	private static int nextID = 0;
	private int id;
	private DSMModel dsmModel = null;
	private CLSXModel clsxModel = null;
	
	public TitanicModel(){
		this.id = TitanicModel.nextID();
	}

    public void save()throws SaveException{

    }

    public void setDsmModel(DSMModel dsmModel){
        /**
         * Titanic의 dsmmodel 을 set 합니다.
         * overwrite 하기 때문에 기존의 dsm 포인터는 사라집니다.
         *
         * @param {@link model.DSMModel}
         */
        this.dsmModel = dsmModel;
    }
    public void setClsxModel(CLSXModel clsxModel){
        /**
         * Titanic의 clsxmodel 을 set 합니다.
         * overwrite 하기 때문에 기존의 clsx 포인터는 사라집니다.
         *
         * @param {@link model.CLSXModel}
         */
        this.clsxModel = clsxModel;
    }
	public GroupNode getGroupNode(){
		GroupNode node =  this.clsxModel.getGroupNode();
		if(node != null)
			return node;
		node = this.dsmModel.getGroupNode();
		return node;
	}
	public int getID(){
		return this.id;
	}

	static private int nextID(){
		return TitanicModel.nextID++;
	}
}
