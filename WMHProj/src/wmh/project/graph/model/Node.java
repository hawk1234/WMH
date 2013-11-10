/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author MAREK
 */
public class Node {
    
    private final String label;
    private final ArrayList<Node> nodes = new ArrayList<Node>();

    public Node(String label) {
        if(label == null){
            throw new GraphException("Label can't be null.");
        }
        
        this.label = label;
    }
    
    void addNode(Node node){
        nodes.add(node);
    }
    
    void removeNode(Node node){
        nodes.remove(node);
    }

    public String getLabel() {
        return label;
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }
    
    public Node getNode(String label) {
        if(label != null){
            for(Node n : nodes){
                if(label.equals(n.getLabel())){
                    return n;
                }
            }
        }
        return null;
    }
}
