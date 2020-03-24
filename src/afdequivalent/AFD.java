package afdequivalent;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


public class AFD {
    protected String alfabeto;
    protected int noEstados;
    protected String estadoInicial;
    protected String estadosFinales;
    protected Node [] estados;
    
    public AFD(String alfabeto,int noEstados){
        this.alfabeto = alfabeto;
        this.noEstados = noEstados;
        this.estadoInicial = "";
        this.estadosFinales = "";
        estados = new Node[noEstados];
    }
    
    
    public void llenarEstados(){
        for(int i=0;i<this.noEstados;i++){
            String name = JOptionPane.showInputDialog("Nombre del Estado No. " + (i+1));
            boolean ini, fina;
            if(this.estadoInicial.equals("")){
               ini= JOptionPane.showConfirmDialog(null, "El Estado '" + name 
                    + "' ¿Es Inicial?", "Estado " + (i+1), JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION;
                 this.estadoInicial = ini ? name:"";
            }else{
                ini=false;
            }
            
            fina=JOptionPane.showConfirmDialog(null, "El Estado '" + name 
                    + "' ¿Es Final?", "Estado " + i, JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION;
            
            this.estadosFinales += fina ? name + "," : ""; 
            
            estados[i] = new Node(name,ini,fina);
            
            estados[i].llenarTransiciones(this.alfabeto);
        }
    }
}
