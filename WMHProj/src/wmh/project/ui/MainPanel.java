/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.ui;

import java.awt.BorderLayout;
import wmh.project.algorithm.ui.AlgorithmSettingsUI;
import wmh.project.framework.explorer.ExplorerPanel;
import wmh.project.graph.model.Graph;
import wmh.project.visual.graph.GraphControler;

/**
 *
 * @author MAREK
 */
public class MainPanel extends javax.swing.JPanel {

    private static MainPanel me;
    
    private Graph graph;
    private GraphControler controler;
    
    /**
     * Creates new form MainPanel
     */
    private MainPanel() {
        initComponents();
        
        explorerPanel.add(new ExplorerPanel(), BorderLayout.CENTER);
        settingsPanel.add(AlgorithmSettingsUI.getInstance());
    }
    
    public static MainPanel getInstance(){
        if(me == null){
            me = new MainPanel();
        }
        return me;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainSplit = new javax.swing.JSplitPane();
        explorerPanel = new javax.swing.JPanel();
        workAreaPanel = new javax.swing.JPanel();
        workAreaSplit = new javax.swing.JSplitPane();
        mainAreaPanel = new javax.swing.JPanel();
        mainScrollArea = new javax.swing.JScrollPane();
        settingsPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        mainSplit.setDividerLocation(150);

        explorerPanel.setLayout(new java.awt.BorderLayout());
        mainSplit.setLeftComponent(explorerPanel);

        workAreaPanel.setLayout(new java.awt.BorderLayout());

        workAreaSplit.setDividerLocation(450);

        mainAreaPanel.setLayout(new java.awt.BorderLayout());
        mainAreaPanel.add(mainScrollArea, java.awt.BorderLayout.CENTER);

        workAreaSplit.setLeftComponent(mainAreaPanel);

        settingsPanel.setLayout(new java.awt.BorderLayout());
        workAreaSplit.setRightComponent(settingsPanel);

        workAreaPanel.add(workAreaSplit, java.awt.BorderLayout.CENTER);

        mainSplit.setRightComponent(workAreaPanel);

        add(mainSplit, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel explorerPanel;
    private javax.swing.JPanel mainAreaPanel;
    private javax.swing.JScrollPane mainScrollArea;
    private javax.swing.JSplitPane mainSplit;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JPanel workAreaPanel;
    private javax.swing.JSplitPane workAreaSplit;
    // End of variables declaration//GEN-END:variables

    public void displayGraph(Graph graph){
        clearDisplay();
        this.graph = graph;
        controler = new GraphControler(graph);
        mainScrollArea.setViewportView(controler.getVisualGraph());
        AlgorithmSettingsUI.getInstance().setEagleView(controler.createEagleView());
    }

    public Graph getDisplayedGraph(){
        return graph;
    }
    
    public void clearDisplay(){
        if(controler != null){
            mainScrollArea.setViewportView(null);
            controler.dispose();
            AlgorithmSettingsUI.getInstance().clearEagleView();
        }
        graph = null;
        controler = null;
    }
}
