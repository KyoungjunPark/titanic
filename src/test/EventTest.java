package test;
import model.Event;
import model.EventManager;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by kimjisoo on 6/3/15.
 */
public class EventTest {
    @Test
    public void EventManagertest(){
        final boolean[] a = {false};
        EventManager.addEvent(new Event("change"){
            public void action(){
                a[0] = true;
            }
        });
        assertFalse(a[0]);
        EventManager.callEvent("change");
        assertTrue(a[0]);
    }
}
