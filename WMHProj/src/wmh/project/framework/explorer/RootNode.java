/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.framework.explorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;

/**
 *
 * @author MAREK
 */
public class RootNode implements TreeNode{

    private ArrayList<GraphNode> children = new ArrayList<GraphNode>();
    private ExplorerModel model;
    
    void addChild(GraphNode graph){
        children.add(graph);
    }
    
    void removeChild(GraphNode graph){
        children.remove(graph);
    }
    
    void setModel(ExplorerModel model){
        this.model = model;
    }
    
    ExplorerModel getModel(){
        return model;
    }
    
    @Override
    public GraphNode getChildAt(int childIndex) {
        return children.get(childIndex);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return null;
    }

    @Override
    public int getIndex(TreeNode node) {
        return children.indexOf(node);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Enumeration children() {
        return Collections.enumeration(children);
    }

    @Override
    public String toString() {
        return "Data";
    }
}
