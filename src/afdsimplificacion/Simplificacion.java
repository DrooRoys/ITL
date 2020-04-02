package afdsimplificacion;

import java.util.Map;
import java.util.TreeMap;

public class Simplificacion {

    private AFD m;
    private AFD copia;
    private AFD m1;
    private AFD m2;
    private int noEstados;
    private String alfabeto;
    protected Map<String, String> transicion;
    private int cont;

    public Simplificacion(AFD a1, String alfa, int noEstados) {
        this.m = a1;
        this.copia = a1;
        this.alfabeto = alfa;
        this.noEstados = noEstados;
    }

    public void simplificarA() {
        AFD t = (copia);
        
        while(t!=null){
            t = simplifica();
            if(t!=null){
                copia= new AFD(t);
                System.out.println("-------------------------------");
                copia.imprimirAutomata();
                System.out.println("-------------------------------");
                //System.out.println("SI LLEGO AQUI");
            }
        }
        
        System.out.println("----- AUTOMATA ORIGINAL -----");
        m.imprimirAutomata();
        System.out.println("----- AUTOMATA SIMPLIFICADO -----");
        copia.imprimirAutomata();
    }
    
      
    private AFD simplifica(){
        AFD temp = null;
        m1 = new AFD(copia);
        m2 = new AFD(copia);
        
//        System.out.println("m1: " + m1);
//        System.out.println("Edo inicial: " + m1.estadoInicial);
//        System.out.println("m2: " + m2);
//        System.out.println("Edo inicial: " + m2.estadoInicial);
        // Equivalencia eq = new Equivalencia(m1,m2,alfabeto);
        //eq.compararAutomatas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial());
        boolean cambio = false;

            for (int i = 0; i < copia.noEstados; i++) {
                for (int j = i+1; j < copia.noEstados; j++) {
                    //if (i != j) {
                        cambiarEdoInicial(m1, i,1);
                        
                        cambiarEdoInicial(m2, j,2);
                        
                        Equivalencia eq = new Equivalencia(m1, m2, alfabeto);
                        
                        //System.out.println("m1 inicial: " + m1.obtenerNodoInicial().nombre);
                        //System.out.println("m2 inicial: " + m2.obtenerNodoInicial().nombre);
                        
                        if (eq.compararAutomatas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial()) == true) {
                            //System.out.println("ENTRO AL IF DE EQUIVALENTES");

                            cambiarEntradas(m1.obtenerNodoInicial(), m2.obtenerNodoInicial());

                            eliminarSalidas(m2.obtenerNodoInicial(), m1.obtenerNodoInicial());
                            temp = new AFD(m2);

                            cambio = true;
                            break;

                        }
                    //}
                }
                if (cambio == true) {
                    break;
                }
            }
            
            return temp;
    }

    private void cambiarEdoInicial(AFD a, int it, int k) {
        Node n;
        n = a.obtenerNodoInicial();
        //System.out.println("-P inicial: " + k+ "/" + n.nombre);
        //System.out.println("Nodo n: " + n);
        n.inicial = false;
        a.estados[it].inicial = true;
        a.setEstadoInicial(a.estados[it].nombre);
        //System.out.println("S inicial: " + k+ "/" + a.estados[it].nombre);
      
    }

    private void cambiarEntradas(Node n1, Node n2) {
        transicion = new TreeMap();
        for (int i = 0; i < noEstados; i++) {
            for (String s : this.alfabeto.split(",")) {
                if (m2.estados[i].transiciones.get(s).equals(n2.nombre)) {
                    m2.estados[i].transiciones.replace(s, n1.nombre);
                }
            }

        }
          //System.out.println("CAMBIA ENTRADAS");

    }

    private void eliminarSalidas(Node n1, Node inicial) {
        //System.out.println(">>>>>>>>>>>>>>node inicial: " + inicial.nombre);
        for (int i = 0; i < noEstados; i++) {
            if(m2.estados[i].nombre.equals(inicial.nombre)){
                m2.estados[i].inicial = true;
                m2.estadoInicial = inicial.nombre;
            }
        }
        m2.eliminarNodo(n1);
        //System.out.println(">>EDO INICIAL M2: " + m2.estadoInicial);
        this.noEstados = m2.getEstados().length;
          //System.out.println("CAMBIA SALIDAS");
    }

}
