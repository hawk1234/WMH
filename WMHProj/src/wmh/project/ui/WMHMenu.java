/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

/**
 *
 * @author MAREK
 */
public class WMHMenu extends JMenuBar{

    public WMHMenu() throws HeadlessException {
        super();
        
        JMenu file = new JMenu("File");
        JMenuItem importItem = new JMenuItem(new ImportAction());
        JSeparator sep = new JSeparator();
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(importItem);
        file.add(sep);
        file.add(exit);
        add(file);
    }
}
