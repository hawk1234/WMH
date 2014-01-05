/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import wmh.project.algorithm.AlgorithmStep;
import wmh.project.framework.DialogDisplayer;
import wmh.project.graph.model.Ant;
import wmh.project.graph.model.Graph;
import wmh.project.graph.model.GraphUtil;
import wmh.project.graph.model.Node;
import wmh.project.ui.MainPanel;
import wmh.project.ui.WMHMenu;
import wmh.project.visual.graph.GraphControler;

/**
 *
 * @author MAREK
 */
public class WMHMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        test();
        init();
    }
    
    private static final void init(){
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                DialogDisplayer.showSimpleErrorDialog("Unexpected error has occurred, program will now close.");
                e.printStackTrace();
                System.exit(-1);
            }
        });
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WMHMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(WMHMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WMHMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex){
            Logger.getLogger(WMHMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFrame frame = new JFrame("WMH");
        JOptionPane.setRootFrame(frame);
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        frame.setJMenuBar(new WMHMenu());
        frame.getContentPane().add(MainPanel.getInstance(), BorderLayout.CENTER);        
        
        frame.setVisible(true);
    }
    
    
    
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST CODE">
    private static final void test(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("WMH_TEST");
        frame.setSize(new Dimension(800, 600));
   
        frame.setLayout(new BorderLayout());
        final GraphControler controler = new GraphControler(GraphUtil.loadGraph("./res/graph_example2.xml"));
//        JScrollPane pane = new JScrollPane(VisualUtil.getVisualGraph(createGraph1()));
//        JScrollPane pane = new JScrollPane(VisualUtil.getVisualGraph(createGraph2()));
        JScrollPane pane = new JScrollPane(controler.getVisualGraph());
//        JScrollPane pane = new JScrollPane(VisualUtil.getVisualGraph(GraphUtil.loadGraph("./res/graph_example1.xml")));
        frame.add(pane, BorderLayout.CENTER);
        frame.setVisible(true);
        JButton animButt = new JButton("DO ANIM");
        animButt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Ant ant = controler.getGraph().getNode("A").getAnts().iterator().next();
                controler.animate(new AlgorithmStep("A", "B", ant.getId()));
            }
        });
        frame.add(animButt, BorderLayout.SOUTH);
        
        controler.getGraph().testInit();
//        GraphUtil.saveGraph(createGraph2(), "./res/graph_example2.xls");
//        GraphUtil.saveGraph(createGraph1(), "./res/graph_example1.xls");
    }
    
    private static Graph createGraph1(){
        Graph ret = new Graph("TEST");
        
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
        Graph ret = new Graph("TEST");
        
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
    //</editor-fold>
}
