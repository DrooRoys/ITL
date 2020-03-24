/*
 * LENGUAJES Y AUTOMATAS I - EQUIVALENCIA ENTRE DOS AFD's (25-03-20)
 * Programa No. 01
 */
package afdequivalent;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class AFDEquivalent {
    private AFD m1;
    private AFD m2;

    public static void main(String[] args) {
        AFDEquivalent e = new AFDEquivalent();
        e.inicio();
    }
    
    public void inicio(){ 
        
       
        String alfabeto = JOptionPane.showInputDialog("Alfabeto (∑={0,1}):");    
        int noEstadosM1 = Integer.parseInt(JOptionPane.showInputDialog("No. de estados M1:"));    
        int noEstadosM2 = Integer.parseInt(JOptionPane.showInputDialog("No. de estados M2 :"));
        
        m1 = new AFD(alfabeto,noEstadosM1);
        m2 = new AFD(alfabeto,noEstadosM2);
        
        m1.llenarEstados();
        m2.llenarEstados();
        
    }
    
}
