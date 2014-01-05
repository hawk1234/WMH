/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.framework.explorer;

import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.tree.TreeNode;
import wmh.project.framework.DialogDisplayer;
import wmh.project.graph.model.Graph;
import wmh.project.ui.MainPanel;

/**
 *
 * @author MAREK
 */
public class GraphNode implements TreeNode, ExplorerActionProvider{

    private RootNode parent;
    private Graph graph;
    
    public GraphNode(RootNode parent, Graph graph) {
        this.parent = parent;
        this.graph = graph;
    }
    
    @Override
    public TreeNode getChildAt(int childIndex) {
        return null;
    }

    @Override
    public int getChildCount() {
        return 0;
    }

    @Override
    public RootNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode node) {
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return false;
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Enumeration children() {
        return null;
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        //TO DO: dodac nazwe dla grafow
        return graph.toString();
    }

    @Override
    public Action[] getActions() {
        return new Action[]{
            new OpenAction(),
            new RemoveAction()
        };
    }
    
    private class OpenAction extends AbstractAction{

        public OpenAction() {
            putValue(NAME, "Open");
        }

        @Override
        public void actionPerformed(ActionEvent e) {            
            MainPanel.getInstance().displayGraph(graph);
        }
    }
    
    private class RemoveAction extends AbstractAction{

        public RemoveAction() {
            putValue(NAME, "Remove");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            getParent().getModel().removeGraph(graph);
        }
    }
    
}
