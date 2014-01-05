/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wmh.project.framework;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author MAREK
 */
public class DialogDisplayer {
    
    /**
     * 
     * @param panel
     * @param dialTitle
     * @return JOptionPane.OK_OPTION || JOptionPane.CANCEL_OPTION
     */
    public static int showSimpleOkCancelDial(JPanel panel, String dialTitle){
        //TO DO: this dialog can't be resized
        return JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(), panel, dialTitle, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
    }
    
    public static String showNameAskDial(String proposedName){
        return (String) JOptionPane.showInputDialog(JOptionPane.getRootFrame(), "Enter Object Name", "Question", JOptionPane.QUESTION_MESSAGE, null, null, proposedName);
    }
    
    public static void showSimpleInformation(String message){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showSimpleErrorDialog(String message){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void showSimpleWarningDialog(String message){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
}
