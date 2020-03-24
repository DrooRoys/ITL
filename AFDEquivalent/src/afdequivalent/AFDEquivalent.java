/*
 * LENGUAJES Y AUTOMATAS I - EQUIVALENCIA ENTRE DOS AFD's (25-03-20)
 * Programa No. 01
 */
package afdequivalent;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class AFDEquivalent {
    public String[] leng;

    public static void main(String[] args) {
        AFDEquivalent e = new AFDEquivalent();
        e.inicio();
    }
    
    public void inicio(){            
        String l = JOptionPane.showInputDialog("Lenguaje (∑={0,1}):");
        leng = new String[l.length()];
        leng = l.split(",");

        int e = Integer.parseInt(JOptionPane.showInputDialog("No. de Estados:"));
        for(int i=0;i<e;i++){
            String name = JOptionPane.showInputDialog("Nombre del Estado No. " + i);
            boolean ini, fina;
            if(JOptionPane.showConfirmDialog(null, "El Estado '" + name 
                    + "' ¿Es Inicial?", "Estado " + i, JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
                ini=true;
            }else{
                ini=false;
            }
            if(JOptionPane.showConfirmDialog(null, "El Estado '" + name 
                    + "' ¿Es Final?", "Estado " + i, JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
                fina=true;
            }else{
                fina=false;
            }
            
            Node node = new Node(name,ini,fina);
        }
    }
    
}
