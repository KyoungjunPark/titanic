package test;
import org.junit.Test;
import util.GroupNode;
import util.ItemNode;

import static org.junit.Assert.*;

/**
 * Created by kimjisoo on 6/3/15.
 */
public class NodeTest {
    @Test
    public void NodeCreatetest(){
        GroupNode root = new GroupNode("root");
        assertEquals(root.print(), "<group name=\"root\">\r\n</group>\r\n");
        root.addItem(new ItemNode("A"));
        assertEquals(root.print(), "<group name=\"root\">\r\n<item name=\"A\" />\r\n</group>\r\n");
        root.addItem(new ItemNode("B"));
        assertEquals(root.print(), "<group name=\"root\">\r\n<item name=\"A\" />\r\n<item name=\"B\" />\r\n</group>\r\n");

        GroupNode c = new GroupNode("C");
        c.addItem(new ItemNode("D"));
        c.addItem(new ItemNode("E"));
        assertEquals(c.print(), "<group name=\"C\">\r\n<item name=\"D\" />\r\n<item name=\"E\" />\r\n</group>\r\n");

        root.addItem(c);
        assertEquals(root.print(), "<group name=\"root\">\r\n<item name=\"A\" />\r\n<item name=\"B\" />\r\n<group name=\"C\">\r\n" +
                "<item name=\"D\" />\r\n" +
                "<item name=\"E\" />\r\n" +
                "</group>\r\n</group>\r\n");

    }
}
