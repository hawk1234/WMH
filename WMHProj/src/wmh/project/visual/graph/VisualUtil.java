/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;

import javax.swing.JComponent;
import wmh.project.graph.model.Graph;
import wmh.project.graph.model.Node;
import wmh.project.graph.model.Relation;

/**
 *
 * @author MAREK
 */
public class VisualUtil {
    
    private VisualUtil(){};
    
    public static JComponent getVisualGraph(Graph graph){
        VisualGraph vg = new VisualGraph();
        
        for(Node n : graph.getNodes()){
            vg.addNode(n);
        }
        for(Relation r : graph.getRelations()){
            vg.addEdge(r);
            vg.setEdgeSource(r, r.getNode1());
            vg.setEdgeTarget(r, r.getNode2());
        }
        
        vg.layout();
        return vg.createView();
    }
}
