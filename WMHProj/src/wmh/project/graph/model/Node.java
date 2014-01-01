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
    private final ArrayList<Ant> ants = new ArrayList<Ant>();
    private final ArrayList<NodeListener> listeners = new ArrayList<NodeListener>();

    public Node(String label) {
        if(label == null){
            throw new GraphException("Label can't be null.");
        }
        
        this.label = label;
    }
    
    public void addNodeListener(NodeListener listener){
        listeners.add(listener);
    }
    
    public void removeNodeListener(NodeListener listener){
        listeners.remove(listener);
    }
    
    protected void fireAntAdded(int id){
        for(NodeListener listener : listeners){
            listener.antAdded(this, id);
        }
    }
    
    protected void fireAntRemoved(int id){
        for(NodeListener listener : listeners){
            listener.antRemoved(this, id);
        }
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
    
    public Ant getAnt(int id){
        for(Ant a : ants){
            if(a.getId() == id){
                return a;
            }
        }
        
        throw new GraphException("No such ant: "+id);
    }
    
    public List<Ant> getAnts(){
        return Collections.unmodifiableList(ants);
    }
    
    public int getAntCount(){
        return ants.size();
    }
    
    public void addAnt(Ant ant){
        ants.add(ant);
        fireAntAdded(ant.getId());
    }
    
    public Ant removeAnt(Ant ant){
        if(ants.remove(ant)){
            fireAntRemoved(ant.getId());
            return ant;
        }
        throw new GraphException("No such ant: "+ant);
    }
    
    public static interface NodeListener{
        public void antAdded(Node source, int id);
        public void antRemoved(Node source, int id);
    }
}
