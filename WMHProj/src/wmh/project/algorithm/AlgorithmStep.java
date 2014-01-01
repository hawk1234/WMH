/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.algorithm;

/**
 *
 * @author MAREK
 */
public class AlgorithmStep {

    public final String source;
    public final String target;
    public final int ant;

    public AlgorithmStep(String source, String target, int ant) {
        this.source = source;
        this.target = target;
        this.ant = ant;
    }
}
