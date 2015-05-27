package util;
/**
 * Created by KimJiSoo on 15. 5. 19..
 */
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class XMLParser {
    /** file 을 받아 XML 을 parsing 합니다.
     *
     * XML 은 다음과 같은 데이터를 가지고 있어야 합니다.
     * TAG = GROUP or ITEM
     * Attributes = name
     * Parsing 된 데이터는 GroupNode 에 담아지게 됩니다.
     * EX )
     * foo.xml
     * <GROUP name="ROOT">
     *   <ITEM name="A"/>
     *   <GROUP name="SUB">
     *     <ITEM name="B"/>
     *     <ITEM name="C"/>
     *   </GROUP>
     * </GROUP>
     * return node =>
     * Node(
     * type GroupNode
     * name null
     * values [ Node(
     *            type GroupNode
     *            name ROOT
     *            values [ Node(type ItemNode, name A),
     *                     Node(
     *                         type GroupNode
     *                         name SUB
     *                         values[Node(type ItemNode, name B), Node(type ItemNode, name C)]
     *                         )
     *                   ]
     *            )
     *       ]
     * )
     *
     * @param file XML 의 파일을 받습니다.
     * @return GroupNode 해당 객체의 자세한 사항은 {@link GroupNode} 를 참고하세요.
     * @throws ParserConfigurationException .
     * @throws SAXException .
     * @throws IOException .
     */
    public GroupNode parseXML(File file) throws ParserConfigurationException,SAXException, IOException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(file);
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        GroupNode groupNode = new GroupNode();
        childNodes(nodeList, groupNode);
        return groupNode.getFirstChildGroupNode();
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
