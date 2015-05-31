package titanic;

import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import model.Event;
import util.GreenTreeNode;
import model.EventManager;
import model.ModelManager;


public class FileTree extends JTree implements Controllerable {
    private GreenTreeNode root;

    public FileTree() {
        this.setModel(null);
    }

    public void makeTree() {

        root = ModelManager.sharedModelManager().getCurrentTitanicModel()
                .getGroupNode().getTreeNode();

        this.setModel(new DefaultTreeModel(root));
        this.collapseRow(0);


        this.setSelectionPath(new TreePath(root));


    }

    /*
        must implement flag clone!!
     */
    protected void moveUp() {

        ArrayList<GreenTreeNode> nodes = getSelectedNodes();
        TreePath[] treePath = new TreePath[nodes.size()];


        nodes = sortFromIndex(nodes);

        for (int i = 0; i < nodes.size(); i++) {
            GreenTreeNode node = nodes.get(i);

            DefaultTreeModel model = (DefaultTreeModel) this.getModel();
            GreenTreeNode root = (GreenTreeNode) model
                    .getRoot();
            GreenTreeNode newNode;

            newNode = deepClone(node);
            model.insertNodeInto(newNode, (GreenTreeNode) node.getParent(),
                    node.getParent().getIndex(node) - 1);

            model.removeNodeFromParent(node);
            treePath[i] = new TreePath(newNode.getPath());
        }

        this.setSelectionPaths(treePath);

        syncWithModel();
    }

    protected void moveDown() {

        ArrayList<GreenTreeNode> nodes = getSelectedNodes();
        TreePath[] treePath = new TreePath[nodes.size()];

        nodes = reverseSortFromIndex(nodes);

        for (int i = 0; i < nodes.size(); i++) {
            GreenTreeNode node = nodes.get(i);
            DefaultTreeModel model = (DefaultTreeModel) this.getModel();
            GreenTreeNode root = (GreenTreeNode) model
                    .getRoot();
            GreenTreeNode newNode;

            newNode = deepClone(node);
            model.insertNodeInto(newNode, (GreenTreeNode) node.getParent(),
                    node.getParent().getIndex(node) + 2);

            model.removeNodeFromParent(node);

            treePath[i] = new TreePath(newNode.getPath());
        }

        this.setSelectionPaths(treePath);

        syncWithModel();
    }

    private GreenTreeNode deepClone(GreenTreeNode source) {
        GreenTreeNode newNode = (GreenTreeNode) source
                .clone();
        Enumeration enumeration = source.children();
        while (enumeration.hasMoreElements()) {
            newNode.add(deepClone((GreenTreeNode) enumeration
                    .nextElement()));
        }
        return newNode;
    }

    private ArrayList<GreenTreeNode> sortFromIndex(ArrayList<GreenTreeNode> nodes) {

        for (int i = 0; i < nodes.size(); i++) {
            int min = root.getIndex(nodes.get(i));
            int index = i;
            for (int j = i + 1; j < nodes.size(); j++) {
                if (root.getIndex(nodes.get(j)) < min) {
                    min = root.getIndex(nodes.get(j));
                    index = j;
                }
            }
            if (i != index) {
                //exchange i & index 's contents
                GreenTreeNode tmpNode = nodes.get(i);
                nodes.set(i, nodes.get(index));
                nodes.set(index, tmpNode);
            }
        }
        return nodes;

    }

    private ArrayList<GreenTreeNode> reverseSortFromIndex(ArrayList<GreenTreeNode> nodes) {

        for (int i = 0; i < nodes.size(); i++) {
            int max = root.getIndex(nodes.get(i));
            int index = i;
            for (int j = i + 1; j < nodes.size(); j++) {
                if (root.getIndex(nodes.get(j)) > max) {
                    max = root.getIndex(nodes.get(j));
                    index = j;
                }
            }
            if (i != index) {
                //exchange i & index 's contents
                GreenTreeNode tmpNode = nodes.get(i);
                nodes.set(i, nodes.get(index));
                nodes.set(index, tmpNode);
            }
        }
        return nodes;
    }

    protected void addItem() {
        DefaultTreeModel model = (DefaultTreeModel) this.getModel();
        GreenTreeNode root = (GreenTreeNode) model.getRoot();
        root.add(new GreenTreeNode("another_child"));
        model.reload(root);
    }

    /**
     * Tree에 존재하는 모든 항목들을 open 상태로 바꿉니다.
     * */
    protected void expandAll(int startingIndex, int rowCount) {
        boolean checkIsEdit = ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit();

        for (int i = startingIndex; i < rowCount; i++) {
            GreenTreeNode node = (GreenTreeNode) this.getPathForRow(i).getLastPathComponent();
            node.setIsExpanded(true);
            this.expandRow(i);
        }

        if (this.getRowCount() != rowCount) {
            expandAll(rowCount, this.getRowCount());
        }

        syncWithModel();
        if(checkIsEdit == false){
            ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().setIsEdit(false);
            ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().setIsEdit(false);
        }
    }

    /**
     * Tree에 존재하는 모든 항목들을 close 상태로 바꿉니다.
     * */
    protected void collapseAll() {

        boolean checkIsEdit = ModelManager.sharedModelManager().getCurrentTitanicModel().isEdit();

        for (int i = this.getRowCount() - 1; i >= 0; i--) {
            GreenTreeNode node = (GreenTreeNode) this.getPathForRow(i).getLastPathComponent();
            node.setIsExpanded(false);
            this.collapseRow(i);
        }

        if(checkIsEdit == false){
            ModelManager.sharedModelManager().getCurrentTitanicModel().getDsmModel().setIsEdit(false);
            ModelManager.sharedModelManager().getCurrentTitanicModel().getClsxModel().setIsEdit(false);
        }
    }

    /**
     * 선택된 항목들을 모두 삭제합니다.
     * ModelManager와 data를 sync합니다.
     * */
    protected void delete() {

        ArrayList<GreenTreeNode> nodes = getSelectedNodes();
        for (int i = 0; i < nodes.size(); i++) {
            ((DefaultTreeModel) this.getModel()).removeNodeFromParent(nodes
                    .get(i));
        }

        syncWithModel();
    }

    /**
     * input의 이름을 가지는 새로운 Node를 만듭니다.
     * 선택된 항목들을 Node의 children으로 이동합니다.
     * Node를 선택된 항목들의 parent에 child로 추가합니다.
     * */
    protected void groupTree(String groupName) {
        ArrayList<GreenTreeNode> nodes = getSelectedNodes();
        GreenTreeNode newGroup = new GreenTreeNode(groupName);

        nodes = sortFromIndex(nodes);
        int index = nodes.get(0).getParent().getIndex(nodes.get(0));
        MutableTreeNode parent = (MutableTreeNode) nodes.get(0).getParent();

        for (int i = 0; i < nodes.size(); i++) {
            GreenTreeNode newNode = new GreenTreeNode();
            newNode = (GreenTreeNode) deepClone(nodes.get(i));
            newGroup.add(newNode);
        }

        ((DefaultTreeModel) this.getModel()).insertNodeInto(newGroup, parent, index);
        delete();
        syncWithModel();

    }

    /**
     * 선택된 group을 해제합니다.
     * group에 속한 children들은 그룹의 parent의 children으로 이동합니다. 
     * */
    protected void unGroupTree() {
        GreenTreeNode node = getSelectedNodes().get(0);
        int index = node.getParent().getIndex(node);
        int childCount = node.getChildCount();

        ArrayList<GreenTreeNode> nodes = new ArrayList<GreenTreeNode>();
        for (int i = 0; i < childCount; i++) {
            //nodes.add((GreenTreeNode) node.getChildAt(i));
            ((DefaultTreeModel) this.getModel()).insertNodeInto
                    ((MutableTreeNode) node.getChildAt(0), (MutableTreeNode) node.getParent(), index + i);
        }
        delete();
        EventManager.callEvent("ungroupButtonDisable");
        syncWithModel();
    }

    private ArrayList<GreenTreeNode> getSelectedNodes() {
        TreePath[] paths = this.getSelectionPaths();
        ArrayList<GreenTreeNode> nodes = new ArrayList<GreenTreeNode>();
        for (TreePath path : paths) {
            nodes.add((GreenTreeNode) path.getLastPathComponent());
        }

        return nodes;

    }
    public GreenTreeNode findNode(String search) {

        Enumeration nodeEnumeration = this.root.breadthFirstEnumeration();
        while (nodeEnumeration.hasMoreElements()) {
            GreenTreeNode node = (GreenTreeNode) nodeEnumeration
                    .nextElement();
            String found = (String) node.getUserObject();
            if (search.equals(found)) {
                return node;
            }
        }
        return null;
    }
    public void rename(GreenTreeNode node, String name) {

        if(node.getChildCount() == 0){ //item node
            node.setUserObject(name);
            syncWithModel();
            EventManager.callEvent("Redraw-Table");
            EventManager.callEvent("FileTree-redraw");

        }else{//group node
            node.setUserObject(name);
            syncWithModel();
            EventManager.callEvent("Redraw-Table");
            EventManager.callEvent("FileTree-redraw");
        }
    }
    public void expandNode(GreenTreeNode node) {
        node.setIsExpanded(true);
        syncWithModel();
    }
    public void collapseNode(GreenTreeNode node) {
        node.setIsExpanded(false);
        syncWithModel();
    }
    public void sortNode(GreenTreeNode node) {
        node.sortAlphabetic();
        syncWithModel();

        this.setSelectionPath(new TreePath(node.getPath()));
        EventManager.callEvent("FileTree-redraw");
    }
    public void makeDefaultNodes(int numNode){

        this.root.getNode().setName("root$");
        for (int i = 0; i < numNode; i++) {
            GreenTreeNode node = new GreenTreeNode("entity_" + (i + 1));
            this.root.add(node);
        }
        syncWithModel();
    }
    public void redrawTree(){
        if(ModelManager.sharedModelManager().getCurrentTitanicModel() != null) {
            this.root = ModelManager.sharedModelManager().getCurrentTitanicModel()
                    .getGroupNode().getTreeNode();
            this.setModel(new DefaultTreeModel(this.root));

        }
        else{
            this.root = null;
            this.setModel(new DefaultTreeModel(this.root));
            return;
        }


        Enumeration nodeEnumeration = this.root.breadthFirstEnumeration();
        while(nodeEnumeration.hasMoreElements()){

            GreenTreeNode node = (GreenTreeNode) nodeEnumeration.nextElement();
            if(node.isExpanded()) {
                this.expandPath(new TreePath(node.getPath()));
            }
        }

    }

    protected void syncWithModel() {
        ModelManager.sharedModelManager().getCurrentTitanicModel().syncTreeNode(this.root);
    }

    @Override
    public void setAction(String title, ActionListener action) {
    }

}
