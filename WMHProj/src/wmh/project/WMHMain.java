/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import wmh.project.graph.model.Graph;
import wmh.project.graph.model.GraphUtil;
import wmh.project.graph.model.Node;
import wmh.project.visual.graph.VisualUtil;

/**
 *
 * @author MAREK
 */
public class WMHMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WMH_TEST");
        frame.setSize(new Dimension(800, 600));
   
        frame.setLayout(new BorderLayout());
//        JScrollPane pane = new JScrollPane(VisualUtil.getVisualGraph(createGraph1()));
        JScrollPane pane = new JScrollPane(VisualUtil.getVisualGraph(createGraph2()));
        frame.add(pane, BorderLayout.CENTER);
        frame.setVisible(true);
        
//        GraphUtil.saveGraph(createGraph2(), "./res/graph_example2.xls");
//        GraphUtil.saveGraph(createGraph1(), "./res/graph_example1.xls");
    }
    
    private static Graph createGraph1(){
        Graph ret = new Graph();
        
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        
        ret.addNode(a);
        ret.addNode(b);
        ret.addNode(c);
        ret.createRelation(a.getLabel(), b.getLabel());
        ret.createRelation(a.getLabel(), c.getLabel());
        return ret;
    }
    
    private static Graph createGraph2(){
        Graph ret = new Graph();
        
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        
        ret.addNode(a);
        ret.addNode(b);
        ret.addNode(c);
        ret.addNode(d);
        ret.addNode(e);
        ret.addNode(f);
        
        ret.createRelation(a.getLabel(), b.getLabel());
        ret.createRelation(a.getLabel(), d.getLabel());
        ret.createRelation(a.getLabel(), c.getLabel());
        ret.createRelation(d.getLabel(), b.getLabel());
        ret.createRelation(d.getLabel(), e.getLabel());
        ret.createRelation(b.getLabel(), c.getLabel());
        ret.createRelation(c.getLabel(), f.getLabel());
        
        return ret;
    }
}
