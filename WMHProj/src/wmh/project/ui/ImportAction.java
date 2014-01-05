/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.ui;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import wmh.project.framework.DialogDisplayer;
import wmh.project.framework.explorer.Explorer;
import wmh.project.graph.model.Graph;
import wmh.project.graph.model.GraphException;
import wmh.project.graph.model.GraphUtil;

/**
 *
 * @author MAREK
 */
public class ImportAction extends AbstractAction {

    public ImportAction() {
        putValue(NAME, "Import");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(false);
        chooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getPath().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML files";
            }
        });
        if (JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(JOptionPane.getRootFrame())) {
            if (chooser.getSelectedFile() == null) {
                DialogDisplayer.showSimpleInformation("No file was choosen");
                return;
            }
            String fileName = chooser.getSelectedFile().getPath();
            
            try{
                Graph graph = GraphUtil.loadGraph(fileName);
                Explorer.getDefault().getModel().addGraph(graph);
            }catch (GraphException ex){
                DialogDisplayer.showSimpleErrorDialog("Error while loading graph");
            }
        }
    }
}
