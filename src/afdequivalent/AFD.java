package afdequivalent;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;

public class AFD {

    protected String alfabeto;
    protected int noEstados;
    protected String estadoInicial;
    protected String estadosFinales;
    protected Node[] estados;
    private boolean compatible=true;

    protected Map<Integer, Node[]> columna0;
    protected Comparacion[] columnas;

    public AFD(String alfabeto, int noEstados) {
        this.alfabeto = alfabeto;
        this.noEstados = noEstados;
        this.estadoInicial = "";
        this.estadosFinales = "";
        estados = new Node[noEstados];
    }

    public void llenarEstados() {
        for (int i = 0; i < this.noEstados; i++) {
            String name = JOptionPane.showInputDialog("Nombre del Estado No. " + (i + 1));
            boolean ini, fina;
            if (this.estadoInicial.equals("")) {
                ini = JOptionPane.showConfirmDialog(null, "El Estado '" + name
                        + "' ¿Es Inicial?", "Estado " + (i + 1), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                this.estadoInicial = ini ? name : "";
            } else {
                ini = false;
            }

            fina = JOptionPane.showConfirmDialog(null, "El Estado '" + name
                    + "' ¿Es Final?", "Estado " + i, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            this.estadosFinales += fina ? name + "," : "";

            estados[i] = new Node(name, ini, fina);

            estados[i].llenarTransiciones(this.alfabeto);
        }
    }

    public Node obtenerNodo(String nombreNodo) {
        Node estado = null;
        for (Node tmp : this.estados) {
            if (tmp.nombre.equals(nombreNodo)) {
                estado = tmp;
            }
        }

        return estado;
    }

    public Node obtenerNodoInicial() {
        Node estado = null;
        for (Node tmp : this.estados) {
            if (tmp.inicial) {
                estado = tmp;
            }
        }

        return estado;
    }

    public boolean nodoEsFinal(Node n) {
        boolean f = false;
        for (Node tmp : this.estados) {
            if (n.fina == true) {
                f = true;
            }
        }

        return f;
    }

    public void compararAutomatas(Node iM1, Node iM2) {
        Node[] aux = new Node[2];
        aux[0] = iM1;
        aux[1] = iM2;
        this.columna0 = new TreeMap();
        
        columna0.put(0, aux);
        
        int i = 0;
        do {
            aux = columna0.get(i);
            System.out.println("Columna 0  -->  " + columna0.get(i).toString());
            verificarEstadosEnColumna0(aux[0],aux[1]);
            i=i+1;
        } while(this.compatible==true);
        
        if(this.compatible==true){
            System.out.println("LOS AUTOMATAS SI SON EQUIVALENTES");
        }else{
            System.out.println("LOS AUTOMATAS NO SON EQUIVALENTES");
        }
    }

    public void verificarEstadosEnColumna0(Node iM1, Node iM2) {
        Node[] aux = new Node[2];
        aux[0] = iM1;
        aux[1] = iM2;
        //this.columna0 = new TreeMap();
        int z = this.alfabeto.split(",").length;
        int k = 0;

        llenarFilaDeSimbolos(aux[0], aux[1]);

        for (int i = 0; i < z; i++) {
            if (!columna0.containsValue(columnas[i].nodos)) {
                k = k + 1;
                columna0.put(k, columnas[i].nodos);
                //System.out.println("Columna 0 -->   " + columna0.get);
            }
        }
    }

    public void llenarFilaDeSimbolos(Node m1, Node m2) {
        int z = this.alfabeto.split(",").length;
        columnas = new Comparacion[z];
        Node[] aux = new Node[2];
        Node a, b;
        aux[0] = m1;
        aux[1] = m2;
        int x = 0;
        for (String simbolo : this.alfabeto.split(",")) {
            a = obtenerNodo(aux[0].getTransicion(simbolo));
            b = obtenerNodo(aux[1].getTransicion(simbolo));

            if (a.fina && b.fina || a.inicial && b.inicial) {
                columnas[x] = new Comparacion(simbolo, a, b);
                System.out.println(simbolo + "  -->  " + a + ", " + b);
                x++;
            } else {
                System.out.println("En el simbolo " + simbolo + "los estados "
                        + a + " y " + b + "NO son compatibles");
                this.compatible = false;
                break;
            }
        } //en este for se genera el array de columnas del alfabeto UNA FILA en moore
        //      a       b       c
        //    q2,r2   q1,r1
    }
}
