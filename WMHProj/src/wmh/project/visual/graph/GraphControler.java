/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.visual.graph;

import javax.swing.JComponent;
import org.netbeans.api.visual.animator.AnimatorEvent;
import org.netbeans.api.visual.animator.AnimatorListener;
import org.netbeans.api.visual.widget.Scene;
import org.netbeans.api.visual.widget.Widget;
import wmh.project.algorithm.AlgorithmStep;
import wmh.project.graph.model.Ant;
import wmh.project.graph.model.Graph;
import wmh.project.graph.model.Node;
import wmh.project.graph.model.Relation;

/**
 *
 * @author MAREK
 */
public class GraphControler implements AnimatorListener{

    private Graph graph;
    private VisualGraph vg;
    
    private AntWidgetEx anim;
    
    public GraphControler(Graph model) {
        this.graph = model;
    }

    public Graph getGraph() {
        return graph;
    }
    
    public JComponent getVisualGraph(){
        checkGraph();
        if(vg.getView() == null){
            vg.createView();
        }
        return vg.getView();
    }
    
    public void animate(AlgorithmStep step){
        checkGraph();
        Node source = graph.getNode(step.source);
        Node target = graph.getNode(step.target);
        Ant moved = source.removeAnt(source.getAnt(step.ant));
        
        Widget sW = vg.findWidget(source);
        Widget tW = vg.findWidget(target);
        
        Node tmp = new Node("TMP");
        tmp.addAnt(moved);
        anim = new AntWidgetEx(vg, tmp);
        anim.target=target;
        vg.addToAnimationLayer(anim);
        anim.setPreferredLocation(sW.getLocation());
        
        vg.getSceneAnimator().animatePreferredLocation(anim, tW.getLocation());
    }
    
    private void checkGraph(){
        if(vg == null){
            vg = new VisualGraph();

            for(Node n : graph.getNodes()){
                vg.addNode(n);
            }
            for(Relation r : graph.getRelations()){
                vg.addEdge(r);
                vg.setEdgeSource(r, r.getNode1());
                vg.setEdgeTarget(r, r.getNode2());
            }

            vg.layout();
            vg.getSceneAnimator().getPreferredLocationAnimator().addAnimatorListener(this);
        }
    }
    
    public void dispose(){
        vg.getSceneAnimator().getPreferredLocationAnimator().removeAnimatorListener(this);
        if(vg != null){
            vg.dispose();
        }
    }

    @Override
    public void animatorFinished(AnimatorEvent ae) {
        if(anim != null){
            vg.removeFromAnimationLayer(anim);
            assert anim.getNode().getAntCount() == 1;
            Ant toTarget = anim.getNode().getAnts().iterator().next();
            anim.target.addAnt(toTarget);
            anim=null;
        }
    }
    
    @Override
    public void animatorStarted(AnimatorEvent ae) {}
    @Override
    public void animatorReset(AnimatorEvent ae) {}
    @Override
    public void animatorPreTick(AnimatorEvent ae) {}
    @Override
    public void animatorPostTick(AnimatorEvent ae) {}
    
    private class AntWidgetEx extends AntWidget{

        Node target;
        
        public AntWidgetEx(Scene scene, Node node) {
            super(scene, node);
        }
    }
}
