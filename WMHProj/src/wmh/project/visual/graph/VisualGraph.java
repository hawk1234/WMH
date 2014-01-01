/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;

import org.netbeans.api.visual.action.ActionFactory;
import org.netbeans.api.visual.graph.GraphScene;
import org.netbeans.api.visual.graph.layout.GridGraphLayout;
import org.netbeans.api.visual.layout.LayoutFactory;
import org.netbeans.api.visual.layout.SceneLayout;
import org.netbeans.api.visual.widget.LayerWidget;
import org.netbeans.api.visual.widget.Widget;
import wmh.project.graph.model.Node;
import wmh.project.graph.model.Relation;

/**
 *
 * @author MAREK
 */
public class VisualGraph extends GraphScene<Node, Relation> {

    private LayerWidget backgroundLayer = new LayerWidget (this);
    private LayerWidget mainLayer = new LayerWidget (this);
    private LayerWidget connectionLayer = new LayerWidget (this);
    private LayerWidget animationLayer = new LayerWidget(this);
    
    private SceneLayout layout = LayoutFactory.createSceneGraphLayout(this, new GridGraphLayout<Node, Relation>().setChecker(true));

    public VisualGraph() {
        
        addChild(backgroundLayer);
        addChild(connectionLayer);
        addChild(mainLayer);
        addChild(animationLayer);
        
        
        getActions().addAction(ActionFactory.createZoomAction());
        getActions().addAction(ActionFactory.createPanAction());
    }
    
    @Override
    protected Widget attachNodeWidget(Node n) {
        NodeWidget widget = new NodeWidget(this, n);
        mainLayer.addChild(widget);
        
        widget.getActions().addAction(ActionFactory.createMoveAction());
        
        return widget;
    }

    @Override
    protected Widget attachEdgeWidget(Relation e) {
        RelationWidget widget = new RelationWidget(this);
        connectionLayer.addChild(widget);
        
        return widget;
    }

    @Override
    protected void attachEdgeSourceAnchor(Relation e, Node oldSource, Node newSource) {
        ((RelationWidget) findWidget(e)).setSourceAnchor(((NodeWidget) findWidget(newSource)).getNodeAnchor());
    }

    @Override
    protected void attachEdgeTargetAnchor(Relation e, Node oldTarget, Node newTarget) {
        ((RelationWidget) findWidget(e)).setTargetAnchor(((NodeWidget) findWidget(newTarget)).getNodeAnchor());
    }
    
    public void layout(){
        layout.invokeLayout();
    }
    
    public void addToAnimationLayer(Widget widget){
        animationLayer.addChild(widget);
    }
    
    public void removeFromAnimationLayer(Widget widget){
        animationLayer.removeChild(widget);
    }
    
    public void dispose(){
        for(Widget w : mainLayer.getChildren()){
            ((NodeWidget) w).dispose();
        }
    }
}
