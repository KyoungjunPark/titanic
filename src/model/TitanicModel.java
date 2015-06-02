package model;

import java.io.File;
import java.util.ArrayList;

import util.*;

public class TitanicModel {

	private static int nextID = 0;
	private int id;
	private DSMModel dsmModel = null;
	private CLSXModel clsxModel = null;
	private boolean isEditModel = false;
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
        if(this.clsxModel.getFilePath() != null)
            this.clsxModel.save();
    }

	public void setDsmModel(DSMModel dsmModel){
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

    /**
     * 해당 TitanicModel 에
     * clsx를 set 합니다.
     * @param file clsx file object 를 받습니다.
     * @throws CreateException file 로부터 clsx 를 생성하기 때문에 CreateException 이 발생할 수 있습니다.
     */
	protected void setClsxModel(File file)throws CreateException{
		if(JSFiles.getFileExtension(file).toLowerCase().compareTo(".clsx") ==0) {
			CLSXModel clsx = new CLSXModel(file);
			this.setClsxModel(clsx);
		}
	}

    /**
     * 해당 TitanicModel 에 set되어있는 dsmModel 을 가져옵니다.
     * 해당 데이터는 항상 존재합니다.
     * @return {@link DSMModel} 데이터
     */
    public DSMModel getDsmModel(){ return this.dsmModel; }

    /**
     * 해당 TitanicModel 에 set되어있는 clsxModel 을 가져옵니다.
     * 해당 데이터는 NULL일 수 있습니다.
     * @return {@link CLSXModel} 데이터
     */
    public CLSXModel getClsxModel(){ return this.clsxModel; }

    /**
     * GroupNode 를 가져옵니다.
     * CLSX 가 있다면, Clsx 의 GroupNode 를 가져오며,
     * 없다면 DSM 의 GroupNode 가 반환됩니다.
     * @return GroupNode 의 형태는 {@link GroupNode} 를 참고하세요.
     */
	public GroupNode getGroupNode(){
		if(this.clsxModel != null){
			return this.clsxModel.getGroupNode();
		}
		return this.dsmModel.getGroupNode();
	}

    /**
     * 해당 TitanicModel 의 ID 를 얻어옵니다.
     * ID 은 유니크합니다.
     * @return 해당 모델의 Integer id
     */
	public int getID(){
		return this.id;
	}

    /**
     * TitanicModel 의 다음 id 를 반환합니다.
     * 해당 아이디는 TitanicModel의 생성시에 사용됩니다.
     * @return 다음 TitanicModel 의 id value
     */
	static private int nextID(){
		return TitanicModel.nextID++;
	}

    /**
     * Table 의 사용할 MatrixData 를 반환합니다.
     * DSMModel 과 CLSXModel 에 상태에 따라 적절한 데이터를 반환합니다.
     * 양식은 다음과 같습니다.
     * ArrayList 를 저장하는 ArrayList 가 있습니다.
     * 내부에 있는 ArrayList 는 String 을 가지며, 첫번째는 아이템의 이름, 그이후로는 디펜던시 데이터가 있습니다.
     * @return arrayList 형태의 데이터
     */
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
        GroupNode node = (GroupNode)root.getGroupNode();
        try{
            if( node.getGroupList().size() == 0 ){
                this.dsmModel.setGroupNode(node);
                this.dsmModel.setIsEdit(true);
            }
            if(this.clsxModel == null)
                this.setClsxModel(new CLSXModel());
            this.clsxModel.setGroupNode(node);
            this.clsxModel.setIsEdit(true);
        }catch (CreateException e){
            return false;
        }
        return true;
    }
    public boolean isEdit(){
        return this.dsmModel.isEdit() ||  (this.clsxModel != null && this.clsxModel.isEdit());
    }

    /**
     * clsxmodel 을 제거합니다.
     * New ClsxModel 등에 이용합니다.
     */
    public void removeClsxModel(){

        this.clsxModel = null;
        this.syncTreeNode(this.dsmModel.getGroupNode().getTreeNode());
        //must change!
        this.clsxModel.setIsEdit(false);
        this.dsmModel.setIsEdit(false);
    }

    /**
     * Node 의 Group 정보를 GroupData 형태로 표시합니다.
     * GroupData 은 다음을 참조하세요. {@link GroupData}
     * 현재 Group 의 depth 는 0이라 가정하며 생략합니다.
     * 이하의 그룹에 대해서는 (depth, 시작 index, 끝 index) 의 GroupData 정보를 갖는 ArrayList 를 반환합니다.
     * @return GroupData 데이터를 갖는 ArrayList 입니다.
     */
    public ArrayList<GroupData> getGroupData(){
        ArrayList<Node> itemList = this.getGroupNode().getAllItemList();
        ArrayList<Node> currentGroupList;
        ArrayList<Node> nextGroupList = this.getGroupNode().getGroupList();
        ArrayList<GroupData> groupData = new ArrayList<GroupData>();
        int depth = 1;
        if(this.getGroupNode().isExpanded() == false)
            return groupData;
        while(nextGroupList.size() != 0){
            currentGroupList = nextGroupList;
            nextGroupList = new ArrayList<Node>();
            for( Node node : currentGroupList){
                GroupNode temp = (GroupNode)node;
                if(temp.isExpanded() == true){
                    ArrayList<Node> currentItemList = temp.getAllItemList();
                    GroupData tuple = new GroupData(depth, currentItemList.get(0).hashCode(), (currentItemList.get(currentItemList.size()-1)).hashCode());
                    if( tuple.getFirst() >= 0 && tuple.getLast() >= 0)
                        groupData.add(tuple);
                    nextGroupList.addAll(temp.getGroupList());
                }else{
                    ItemNode firstItem = (ItemNode)((GroupNode) node).getAllItemList().get(0);
                    ItemNode lastItem = (ItemNode)((GroupNode) node).getAllItemList().get(((GroupNode) node).getAllItemList().size() - 1);
                    ArrayList<Node> tempList = ((GroupNode) node).getAllItemList();
                    int firstIndex= itemList.indexOf(firstItem);
                    int lastIndex= itemList.indexOf(lastItem);
                    if(firstIndex == -1 || lastIndex == -1) continue;
                    GroupData tuple = new GroupData(depth, firstItem.hashCode(), firstItem.hashCode());
                    groupData.add(tuple);
                    while (lastIndex > firstIndex){
                        Node tempNode = itemList.get(lastIndex);
                        for( GroupData t : groupData){
                            if(t.getLast() == tempNode.hashCode())
                                t.setLast(itemList.get(lastIndex-1).hashCode());
                        }
                        itemList.remove(lastIndex--);
                    }
                }
            }
            depth++;
        }
        for( GroupData t : groupData){
            for(int i = 0 ; i < itemList.size() ; i++){
                Node node = itemList.get(i);
                if(t.getFirst() == node.hashCode())
                    t.setFirst(i);
                if(t.getLast() == node.hashCode())
                    t.setLast(i);
            }
        }
        return groupData;
    }

    /**
     * Item 을 추가합니다.
     * 해당 아이템은 DSM 에 가장 하단에 위치하며, clsx 에 가장 하단의 Item 으로 존재하게 됩니다.
     * 디펜던시는 0으로 셋됩니다.
     * @param newNodeName 새로 만들 item 의 이름
     */
    public void addNode(String newNodeName){
        if( this.getDsmModel().isExistName(newNodeName) ) return;
        if(this.getClsxModel() != null){
            this.getClsxModel().getGroupNode().addItem(new ItemNode(newNodeName));
        }
        this.getDsmModel().addNode(newNodeName);
    }

    /**
     * 아이템을 rename 합니다. originameName 을 changedName 으로 바꿉니다.
     * 성공여부를 결과값으로 돌려줍니다.
     * @param originalName 바꿀 아이템의 원본 이름
     * @param changedName 바꿀 이름
     * @return 성공시 true, 실패시 false 해당 return 값을 확인 후 실제 노드의 이름을 바꿔주세요.
     */
    public boolean rename(String originalName, String changedName){
        if(!this.getDsmModel().rename(originalName, changedName)) return false;
        if(this.getClsxModel() != null){
            for( Node node : this.getClsxModel().getGroupNode().getAllItemList() ){
                if(node.getName().compareTo(originalName) == 0){
                    node.setName(changedName);
                    return true;
                }
            }
        }
        return false;
    }
    protected void setMetaModel(String nodeName){
        if(this.getClsxModel() != null)
            this.getClsxModel().setMetaModel(nodeName);
        else
            this.getDsmModel().setMetaModel(nodeName);
    }

    public boolean isEditModel() {
        return isEditModel;
    }

    public void setEditModel(boolean isEditModel) {
        this.isEditModel = isEditModel;
    }
    protected void adjustMetaMode(){
        if(this.isEditModel()){
            if(this.getClsxModel() != null)
                this.getClsxModel().loadMetaModel();
            this.getDsmModel().loadMetaModel();
        }else{
            if(this.getClsxModel() != null)
                this.getClsxModel().saveMetaModel();
            this.getDsmModel().saveMetaModel();
        }
    }
}
