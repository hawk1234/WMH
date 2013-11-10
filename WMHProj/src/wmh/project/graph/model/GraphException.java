/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.graph.model;

/**
 *
 * @author MAREK
 */
public class GraphException extends RuntimeException{

    public GraphException(String message) {
        super(message);
    }

    public GraphException(String message, Throwable cause) {
        super(message, cause);
    }
}
