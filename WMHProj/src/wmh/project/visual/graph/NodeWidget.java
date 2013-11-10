/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;

import java.awt.*;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.vmd.VMDNodeAnchor;
import org.netbeans.api.visual.widget.Widget;
import wmh.project.graph.model.Node;

/**
 *
 * @author MAREK
 */
public class NodeWidget extends Widget{

    public static final int WIDGET_WIDTH = 50;
    public static final int WIDGET_HEIGHT = 50;
    
    private Anchor nodeAnchor;
    private Node node;
    
    public NodeWidget(VisualGraph scene, Node node) {
        super(scene);
        this.node = node;
        setPreferredSize(new Dimension(WIDGET_WIDTH, WIDGET_HEIGHT));
        
        nodeAnchor = new VMDNodeAnchor(this);
    }

    @Override
    protected void paintWidget() {
        Graphics2D g2d = getGraphics();
        
        Paint cache = g2d.getPaint();
        
        g2d.setPaint(Color.RED);
        g2d.fillOval(0, 0, getPreferredSize().width, getPreferredSize().height);
        
        g2d.setPaint(Color.BLACK);
        Font cacheFont = g2d.getFont();
        g2d.setFont(new Font(cacheFont.getName(), cacheFont.getStyle(), 20));
        g2d.drawString(node.getLabel(), WIDGET_WIDTH-4*WIDGET_WIDTH/6, 4*WIDGET_HEIGHT/6);
        g2d.setFont(cacheFont);
        g2d.setPaint(cache);
    }

    public Anchor getNodeAnchor() {
        return nodeAnchor;
    }
}
