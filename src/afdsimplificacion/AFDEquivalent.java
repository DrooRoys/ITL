/*
 * LENGUAJES Y AUTOMATAS I - EQUIVALENCIA ENTRE DOS AFD's (25-03-20)
 * Programa No. 01
 *por:
 *  GOMEZ GUIZAR CRISTIAN ROGELIO
 *  LOPEZ SOTO IVAN DE JESUS
 */
package afdsimplificacion;

import javax.swing.JOptionPane;

public class AFDEquivalent {
    protected AFD m1;
    protected AFD m2;
    protected AFD m3;
    public String alfabeto;

    public static void main(String[] args) {
        AFDEquivalent e = new AFDEquivalent();
        //e.inicio();
        //e.comprobarEquivalencia();
        e.simplificarAFD();
    }
    
    public void inicio(){ 
        alfabeto = JOptionPane.showInputDialog("Alfabeto (∑={0,1}):");    
        int noEstadosM1 = Integer.parseInt(JOptionPane.showInputDialog("No. de estados M1:"));
        m1 = new AFD(alfabeto,noEstadosM1);
        m1.llenarEstados();
        
        int noEstadosM2 = Integer.parseInt(JOptionPane.showInputDialog("No. de estados M2 :"));
        m2 = new AFD(alfabeto,noEstadosM2);
        m2.llenarEstados();
    }
    
    public void comprobarEquivalencia(){
        Equivalencia eq = new Equivalencia(m1,m2,alfabeto);
        eq.compararAutomatas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial());
    }
    
    public void simplificarAFD(){
        alfabeto = JOptionPane.showInputDialog("Alfabeto (∑={0,1}):");    
        int noEstadosM1 = Integer.parseInt(JOptionPane.showInputDialog("No. de estados M1:"));
        m3 = new AFD(alfabeto,noEstadosM1);
        m3.llenarEstados();
        
        Simplificacion sim = new Simplificacion(m3,alfabeto,noEstadosM1);
        sim.simplificarA();
        
    }
}
