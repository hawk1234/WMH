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
public class Graph {

    private final ArrayList<Node> nodes = new ArrayList<Node>();
    private final ArrayList<Relation> relations = new ArrayList<Relation>();   
    private AntHill antHill = new AntHill();
    private final String name;
    
    public Graph(String name) {
        this.name = name;
    }
    
    public boolean addNode(Node node){
        if(!containsNode(node.getLabel())){
            nodes.add(node);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean removeNode(String node){
        Node toRemove = getNode(node);
        if(toRemove != null){
            for(Relation r : relations){
                if(r.contains(node)){
                    removeRelation(r);
                }
            }
        }
        return nodes.remove(toRemove);
    }
    
    public boolean createRelation(String n1, String n2){
        if(areConnected(n1, n2)){
            return false;
        }
        Node node1 = getNode(n1);
        Node node2 = getNode(n2);
        
        if(node1 != null && node2 != null){
            return createRelation(node1, node2);
        }
        return false;
    }
    
    boolean createRelation(Node n1, Node n2){
        n1.addNode(n2);
        n2.addNode(n1);
        relations.add(new Relation(n1, n2));
        return true;
    }
    
    public boolean removeRelation(String n1, String n2){
        Relation r = getConnected(n1, n2);
        
        if(r != null){
            return removeRelation(r);
        }
        return false;
    }
    
    boolean removeRelation(Relation relation){
        Node n1 = relation.getNode1();
        Node n2 = relation.getNode2();
        n1.removeNode(n2);
        n2.removeNode(n1);
        return relations.remove(relation);
    }
    
    public Node getNode(String label){
        if(label != null){
            for(Node n : nodes){
                if(label.equals(n.getLabel())){
                    return n;
                }
            }
        }
        return null;
    }
    
    public boolean containsNode(String label){
        return getNode(label) != null;
    }
    
    public boolean areConnected(String l1, String l2){
        return getConnected(l1, l2) != null;
    }

    public List<Node> getNodes() {
        return Collections.unmodifiableList(nodes);
    }

    public List<Relation> getRelations() {
        return Collections.unmodifiableList(relations);
    }
    
    private Relation getConnected(String l1, String l2){
        for(Relation r : relations){
            if(r.contains(l1) && r.contains(l2)){
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        if(name != null){
            return name;
        }else{
            return super.toString();
        }
    }
    
    
    //FOR TEST
    public void testInit(){
        int antMax = 20;
        for(Node node : nodes){
            int antCount = (int) Math.round(antMax*Math.random()) + 1;
            while(antCount > 0){
                node.addAnt(antHill.create());
                --antCount;
            }
        }
    }
}
