/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.framework.explorer;

import java.util.ArrayList;
import javax.swing.tree.DefaultTreeModel;
import wmh.project.graph.model.Graph;
import wmh.project.ui.MainPanel;

/**
 *
 * @author MAREK
 */
public class ExplorerModel extends DefaultTreeModel{

    public ExplorerModel(RootNode root) {
        super(root, true);
        
        root.setModel(this);
    }
    
    public void addGraph(Graph graph){
        if(find(graph) != null){
            throw new IllegalArgumentException("Graph already exist.");
        }
        ((RootNode) getRoot()).addChild(new GraphNode((RootNode) getRoot(), graph));
        nodeStructureChanged((RootNode) getRoot());
    }
    
    public void removeGraph(Graph graph){
        GraphNode toRemove = find(graph);
        if(toRemove != null){
            ((RootNode) getRoot()).removeChild(toRemove);
            if (MainPanel.getInstance().getDisplayedGraph() == graph){
                MainPanel.getInstance().clearDisplay();
            }
            nodeStructureChanged((RootNode) getRoot());
        }
    }
    
    public ArrayList<Graph> getGraphs(){
        RootNode myRoot = (RootNode) getRoot();
        ArrayList<Graph> ret = new ArrayList<Graph>();
        for(int i = 0; i < myRoot.getChildCount(); ++i){
            ret.add(myRoot.getChildAt(i).getGraph());
        }
        return ret;
    }
    
    public GraphNode find(Graph graph){
        int size = ((RootNode) getRoot()).getChildCount();
        for(int i=0; i<size; ++i){
            if(graph.equals(((RootNode) getRoot()).getChildAt(i).getGraph())){
                return ((RootNode) getRoot()).getChildAt(i);
            }
        }
        return null;
    }
}
