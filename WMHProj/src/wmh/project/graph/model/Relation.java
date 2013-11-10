/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.graph.model;

/**
 *
 * @author MAREK
 */
public class Relation {
    
    private final Node node1;
    private final Node node2;

    public Relation(Node node1, Node node2) {
        this.node1 = node1;
        this.node2 = node2;
    }
    
    public boolean contains(String label){
        return getNode(label) != null;
    }

    public Node getNode(String label) {
        if(label != null){
            if(label.equals(node1.getLabel())){
                return node1;
            }else if(label.equals(node2.getLabel())){
                return node2;
            }
        }
        return null;
    }

    public Node getNode1() {
        return node1;
    }

    public Node getNode2() {
        return node2;
    }
}
