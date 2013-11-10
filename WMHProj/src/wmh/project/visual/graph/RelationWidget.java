/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;

import java.awt.BasicStroke;
import org.netbeans.api.visual.widget.ConnectionWidget;

/**
 *
 * @author MAREK
 */
public class RelationWidget extends ConnectionWidget{

    public static final int STROKE_SIZE = 4;
    
    public RelationWidget(VisualGraph scene) {
        super(scene);
        setStroke(new BasicStroke(STROKE_SIZE));
    }
}
