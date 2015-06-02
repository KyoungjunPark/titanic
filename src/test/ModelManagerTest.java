package test;
import model.ModelManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by kimjisoo on 6/2/15.
 */
public class ModelManagerTest {
    @Test
    public void ModelManagerCreatetest(){
        ModelManager modelManager = ModelManager.sharedModelManager();
        assertEquals(modelManager, ModelManager.sharedModelManager()); // 유일 객체 체크
    }
    @Test
    public void ModelManagerCreateTitanicModeltest(){

    }
}
