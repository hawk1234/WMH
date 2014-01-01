/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;

import java.awt.*;
import org.netbeans.api.visual.anchor.Anchor;
import org.netbeans.api.visual.widget.Widget;
import wmh.project.graph.model.Node;

/**
 *
 * @author MAREK
 */
public class NodeWidget extends Widget implements Node.NodeListener{

    public static final int WIDGET_WIDTH = 60;
    public static final int WIDGET_HEIGHT = 60;
    
    private Anchor nodeAnchor;
    private AntWidget antWidget;
    private Node node;
    
    public NodeWidget(VisualGraph scene, Node node) {
        super(scene);
        this.node = node;
        
        //THIS HAS TO BE SET !!!!!!!!!!!!!!!
//        setPreferredSize(new Dimension(WIDGET_WIDTH, WIDGET_HEIGHT));//WHY THE SIZE MUST BE SET AND SETTING BOUNDS IS NOT ENOUGH?
        setPreferredBounds(new Rectangle(0, 0, WIDGET_WIDTH, WIDGET_HEIGHT));//PREFERED BOUNDS WILL BE SET AS NODE ACTUAL BOUNDST WHEN resolveBounds(null, null) IS CALLED
        resolveBounds(null, null);//THIW IS USED TO DETERMIN NODE SIZE BEFORE RESOLVING ANCHOR POSITIONS IT WONT BE DONE AUTOMATICALY BECAUSE CONNECTION LAYER IS UNDER MAIN NODE LAYER
        
        nodeAnchor = new NodeAnchor(this);
        antWidget = new AntWidget(scene, node);
        addChild(antWidget);
        node.addNodeListener(this);
    }    
    
    @Override
    protected void paintWidget() {        
        Graphics2D g2d = getGraphics();
        Shape clipcache = g2d.getClip();
        g2d.setClip(new Rectangle(getBounds()));
        Paint cache = g2d.getPaint();
        
        g2d.setPaint(Color.RED);
        g2d.fillOval(0, 0, getBounds().width, getBounds().height);
        
        g2d.setPaint(Color.BLACK);
        Font cacheFont = g2d.getFont();
        g2d.setFont(new Font(cacheFont.getName(), cacheFont.getStyle(), 20));
        g2d.drawString(node.getLabel(), getBounds().width-getBounds().width/2, getBounds().height/2);

        g2d.setFont(cacheFont);
        g2d.setPaint(cache);
        g2d.setClip(clipcache);
    }

    public Anchor getNodeAnchor() {
        return nodeAnchor;
    }
    
    public void dispose(){
        node.removeNodeListener(this);
    }

    @Override
    public void antAdded(Node source, int id) {
        revalidate();
        repaint();
    }

    @Override
    public void antRemoved(Node source, int id) {
        revalidate();
        repaint();
    }
}
