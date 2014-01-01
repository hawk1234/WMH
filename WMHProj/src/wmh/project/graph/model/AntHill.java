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
class AntHill {

    private int counter=0;
    //TO DO: generate color;
    
    Ant create(){
        Ant ret = new Ant(counter++, Color.yellow);
        return ret;
    }
}
