/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;


import java.awt.*;
import java.util.List;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import wmh.project.graph.model.Ant;
import wmh.project.graph.model.Node;

/**
 *
 * @author MAREK
 */
public class AntWidget extends Widget{

    public static final int ANT_SIZE=8;
    public static final int ANT_SPACE=1;
    
    private Node node;
    
    public AntWidget(Scene scene, Node node) {
        super(scene);
        
        //THIS HAS TO BE SET !!!!!!!!!!!!!!!
        setPreferredBounds(new Rectangle(0, 0, NodeWidget.WIDGET_WIDTH, NodeWidget.WIDGET_HEIGHT));//PREFERED BOUNDS WILL BE SET AS NODE ACTUAL BOUNDST WHEN resolveBounds(null, null) IS CALLED
        resolveBounds(null, null);//THIW IS USED TO DETERMIN NODE SIZE BEFORE RESOLVING ANCHOR POSITIONS IT WONT BE DONE AUTOMATICALY BECAUSE CONNECTION LAYER IS UNDER MAIN NODE LAYER
        
        this.node=node;
    }
    
    private Rectangle getAntRect(int antCount){
        double surface = Math.pow(ANT_SIZE+ANT_SPACE, 2)*antCount;
        double surfaceSide = Math.sqrt(surface);
        
        int lowerLimit = (int) Math.floor(surfaceSide/(ANT_SIZE+ANT_SPACE));
        if(Math.pow(lowerLimit, 2) < antCount){
            lowerLimit += 1;
        }
        
        int x,y,width=lowerLimit*(ANT_SIZE+ANT_SPACE)+ANT_SPACE,height=lowerLimit*(ANT_SIZE+ANT_SPACE)+ANT_SPACE;
        x = (getBounds().width-width)/2;
        if(x < 0){
            x=0;
            width=getBounds().width;
        }
        y = (getBounds().height-height)/2;
        if(y < 0){
            y=0;
            height=getBounds().height;
        }
        return new Rectangle(x, y, width, height);
    }

    @Override
    protected void paintWidget() {
        if(node.getAntCount() <= 0){
            return;
        }
        
        Graphics2D g2d = getGraphics();
        Shape clipcache = g2d.getClip();
        g2d.setClip(new Rectangle(getBounds()));
        Paint cache = g2d.getPaint();
        
        g2d.setPaint(Color.GREEN);
        Stroke cacheStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(1));
        List<Ant> ants = node.getAnts();
        Rectangle antRect = getAntRect(ants.size());
        int counter=0;
        for(int x=ANT_SPACE; x<antRect.width; x+=(ANT_SIZE+ANT_SPACE)){
            for(int y=ANT_SPACE; y<antRect.height; y+=(ANT_SIZE+ANT_SPACE)){
                assert counter < ants.size();
                g2d.setPaint(ants.get(counter).getColor());
                g2d.fillOval(x+antRect.x, y+antRect.y, ANT_SIZE, ANT_SIZE);
                ++counter;
                if(counter >= ants.size()){
                    break;
                }
            }
            if(counter >= ants.size()){
                break;
            }
        }
        g2d.setPaint(Color.BLACK);
        g2d.drawRect(antRect.x, antRect.y, antRect.width, antRect.height);
        
        g2d.setStroke(cacheStroke);
        g2d.setPaint(cache);
        g2d.setClip(clipcache);
    }

    public Node getNode() {
        return node;
    }
}
