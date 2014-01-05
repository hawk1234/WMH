/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.framework.explorer;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.*;

/**
 *
 * @author MAREK
 */
public class Explorer extends JTree{

    private static Icon GRAPH_ICON = null;
    
    static{
        try {
            GRAPH_ICON = new ImageIcon(ImageIO.read(Explorer.class.getResourceAsStream("resources/chart_line.png")));
        } catch (IOException ex) {
            Logger.getLogger(Explorer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Explorer me = null;
    
    private Explorer(TreeModel newModel) {
        super(newModel);
        
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        addMouseListener(new ExplorerAdapter());
    }

    public static Explorer getDefault(){
        if(me == null){
            me = new Explorer(new ExplorerModel(new RootNode()));
        }
        return me;
    }

    @Override
    public TreeCellRenderer getCellRenderer() {
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer(){

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                Component ret;
                if(value instanceof GraphNode){
                    Icon cache = getLeafIcon();
                    setLeafIcon(GRAPH_ICON);
                    ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                    ((JComponent) ret).setToolTipText(((GraphNode) value).getGraph().toString());
                    setLeafIcon(cache);
                }else{
                    ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                }
                return ret;
            }
        };
        return renderer;
    }
    
    @Override
    public ExplorerModel getModel() {
        return (ExplorerModel) super.getModel();
    }
    
    private class ExplorerAdapter extends MouseAdapter{

        @Override
        public void mouseReleased(MouseEvent e) {
            assert getSelectionCount() == 0 || getSelectionCount() == 1;
            if(e.isPopupTrigger() && getSelectionCount() == 1){
                TreeNode node = (TreeNode) getSelectionPath().getLastPathComponent();
                if(node instanceof ExplorerActionProvider){
                    new ExplorerPopup(e.getPoint(), (ExplorerActionProvider) node);
                }
            }
        }
    }
    
    private class ExplorerPopup extends JPopupMenu{

        public ExplorerPopup(Point location, ExplorerActionProvider provider) {
            
            for(Action action : provider.getActions()){
                if(action == null){
                    addSeparator();
                }else{
                    add(action);
                }
            }
            
            show(Explorer.getDefault(), location.x, location.y);
        }
    }
}
