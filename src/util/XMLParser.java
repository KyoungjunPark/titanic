package util;
/**
 * Created by KimJiSoo on 15. 5. 19..
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLParser {
    public GroupNode parseXML(File file) throws ParserConfigurationException,SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(file);
        ArrayList<util.Node> nodeArray = new ArrayList<util.Node>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        GroupNode groupNode = new GroupNode();
        childNodes(nodeList, groupNode);
        return groupNode;
    }
    private void childNodes(NodeList nodeList, GroupNode parentNode){
        for (int i = 0; i < nodeList.getLength(); i++) {
            org.w3c.dom.Node node = nodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element elem = (Element)node;
                String name = node.getAttributes().getNamedItem("name").getNodeValue();
                String tag = elem.getTagName().toLowerCase();
                if( tag == "group" ){
                    NodeList childNodeList = node.getChildNodes();
                    GroupNode groupNode = new GroupNode(parentNode, name);
                    parentNode.addItem(groupNode);
                    childNodes(childNodeList, groupNode);
                }else if( tag == "item" ){
                    ItemNode itemNode = new ItemNode(parentNode, name);
                    parentNode.addItem(itemNode);
                }
            }
        }
    }
}
