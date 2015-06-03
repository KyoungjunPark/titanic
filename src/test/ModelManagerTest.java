package test;
import model.*;
import org.junit.Test;
import util.GreenTreeNode;
import util.GroupNode;
import util.Node;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by kimjisoo on 6/2/15.
 */
public class ModelManagerTest {
    ModelManager modelManager = ModelManager.sharedModelManager();
    @Test
    public void ModelManagerCreatetest(){
        assertEquals(modelManager, ModelManager.sharedModelManager()); // 유일 객체 체크
    }
    @Test
    public void ModelManagerCreateTitanicModeltest(){
        // 받은 id 가 존재하는지 체크
        int id = modelManager.createTitanicModel(10);
        assertTrue(modelManager.isExistModel(id));

        // 받은 id 를 set 후 titanicmodel 을 받아와 같은 id 인지 체크
        modelManager.setCurrentID(id);
        TitanicModel model = modelManager.getCurrentTitanicModel();
        assertEquals(model.getID(), id);

        //file 로 부터 titanicmodel 을 생성하여 set 후 이전 model 과 다른 model 인지 비교
        try {
            id = modelManager.createTitanicModel(new File("test/moka.dsm"));
        } catch (CreateException e) {
            fail(e.toString());
        }
        assertTrue(modelManager.isExistModel(id));
        modelManager.setCurrentID(id);
        assertTrue(model != modelManager.getCurrentTitanicModel());

        model = modelManager.getCurrentTitanicModel();
        assertEquals(model, modelManager.getTitanicModel(id));

        // 존재하지 않는 file
        try {
            id = modelManager.createTitanicModel(new File("test/mosddsadsasadsaka.dsm"));
            fail(id+"");
        } catch (CreateException e) {
        }
    }
    @Test
    public void ModelManagerMethodtest(){
        // 비정상 id set
        assertFalse(modelManager.isExistModel(21312));
        assertFalse(modelManager.setCurrentID(23222));
    }
    @Test
     public void ModelManagerDuplicatetest(){
        int id =  0;
        try {
            id = modelManager.createTitanicModel(new File("test/moka.dsm"));
            modelManager.setCurrentID(id);
            modelManager.setClsx(new File("test/moka_ArchDRH.clsx"));
        } catch (CreateException e) {
            fail(e.toString());
        }
        TitanicModel currentTitanicModel = modelManager.getCurrentTitanicModel();
        ArrayList<Node> groupNodeArray = currentTitanicModel.getGroupNode().getAllGroupList();
        id = modelManager.duplicateTitanicModel(currentTitanicModel.getID(), groupNodeArray.get(groupNodeArray.size()-1).getTreeNode());
        modelManager.setCurrentID(id);
        // model 생성 및 duplicate

        assertEquals(groupNodeArray.get(groupNodeArray.size()-1).toString(), modelManager.getCurrentTitanicModel().getGroupNode().toString());

        // 없는 id 로 duplicate 시도, -1 은 실패를 의미
        assertTrue(modelManager.duplicateTitanicModel(123123, groupNodeArray.get(groupNodeArray.size()-1).getTreeNode()) == -1);
    }
    @Test
    public void ModelManagerEdittest(){
        int id =  0;
        try {
            id = modelManager.createTitanicModel(new File("test/moka.dsm"));
            modelManager.setCurrentID(id);
            modelManager.setClsx(new File("test/moka_ArchDRH.clsx"));
        } catch (CreateException e) {
            fail(e.toString());
        }
        TitanicModel currentTitanicModel = modelManager.getCurrentTitanicModel();
        ArrayList<Node> groupNodeArray = currentTitanicModel.getGroupNode().getAllGroupList();
        id = modelManager.editTatanicModel(currentTitanicModel.getID(), groupNodeArray.get(groupNodeArray.size() - 1).getTreeNode());
        modelManager.setCurrentID(id);

        /*
        Edit 한 Model 에 이름를 바꾸고 원본에 해당하는  데이터의 이름이 변경되었는지 확인한다.
         */
        modelManager.getCurrentTitanicModel().rename(modelManager.getCurrentTitanicModel().getGroupNode().getAllItemList().get(0).getName(), "AAA");
        modelManager.getCurrentTitanicModel().syncTreeNode(modelManager.getCurrentTitanicModel().getGroupNode().getTreeNode());
        groupNodeArray = currentTitanicModel.getGroupNode().getAllItemList();
        boolean find = false;
        for(Node node : groupNodeArray){
            if(node.getName().compareTo("AAA") == 0)
                find = true;
        }
        assertTrue(find);

        // 없는 id 로 edit 시도, -1 은 실패를 의미
        assertTrue(modelManager.editTatanicModel(123123, groupNodeArray.get(groupNodeArray.size() - 1).getTreeNode()) == -1);
    }
}
