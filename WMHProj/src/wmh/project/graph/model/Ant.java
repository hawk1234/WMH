/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.graph.model;

import java.awt.Color;

/**
 *
 * @author MAREK
 */
public final class Ant {
    
    private int id;
    private Color color;
    //TO DO: pamientanie sciezki

    Ant(int id, Color color) {
        this.id = id;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }    
}
