package afdequivalent;

import java.util.Map;
import java.util.TreeMap;

public class Equivalencia {

    private AFD m1;
    private AFD m2;
    private String alfabeto;
    private int k = 0;

    protected Map<Integer, Node[]> columna0;
    protected Comparacion[] columnas;

    private boolean compatible = true;
    int z = 0;

    public Equivalencia(AFD a1, AFD a2, String alfa) {
        this.m1 = a1;
        this.m2 = a2;
        this.alfabeto = alfa;
    }

    public void compararAutomatas(Node iM1, Node iM2) {
        Node[] aux = new Node[2];
        aux[0] = iM1;
        aux[1] = iM2;
        this.columna0 = new TreeMap();
        boolean b = false;

        columna0.put(0, aux);

        int i = 0;
        while (this.compatible == true && b == false) {

            for (int r = 0; r < columna0.size(); r++) {
                Node[] t = columna0.get(r);
                System.out.println("1- " + t[0].nombre + "\n2- " + t[1].nombre);
            }

            //llenarFilaDeSimbolos(aux[0], aux[1]);
            aux = columna0.get(i);
            //if (aux != null) {
            System.out.println("AUXILIAR ACTUAL  -->  " + aux[0].nombre + ", " + aux[1].nombre);

            b = verificarEstadosEnColumna0(aux[0], aux[1]);
            i = i + 1;
            //}
        }

        if (this.compatible == true) {
            System.out.println("LOS AUTOMATAS SI SON EQUIVALENTES");
        } else {
            System.out.println("LOS AUTOMATAS NO SON EQUIVALENTES");
        }

    }

    public boolean verificarEstadosEnColumna0(Node iM1, Node iM2) {
        Node[] aux = new Node[2];
        System.out.println("ENTRA A VERIFICAR ESTADOS EN COLUMNA");
        aux[0] = iM1;
        aux[1] = iM2;
        //this.columna0 = new TreeMap();
        this.z = this.alfabeto.split(",").length;
        //boolean[] bo = new boolean[this.z];

//        int k = 0;
        int t = 1;

        llenarFilaDeSimbolos(aux[0], aux[1]);

        for (int i = 0; i < z; i++) {
            if (!columna0.containsValue(columnas[i].nodos)) {
                k = k + 1;
                columna0.put(k, columnas[i].nodos);
                //bo[i] = true;
                System.out.println("ENTRA AL IF de columna 0");
            } else {
                t = t + 1;
                System.out.println("T= " + t);
            }
        }
        return (t >= z);

    }

    public void llenarFilaDeSimbolos(Node m1, Node m2) {
        //int z = m1     alfabeto.split(",").length;
        columnas = new Comparacion[z];
        Node[] aux = new Node[2];
        Node a = null;
        Node b = null;
        aux[0] = m1;
        aux[1] = m2;
        int x = 0;
        String zz = "";
        for (String simbolo : this.alfabeto.split(",")) {

            a = this.m1.obtenerNodo(aux[0].getTransicion(simbolo));
            System.out.println(" " + a.nombre);
            try {
                zz = aux[1].getTransicion(simbolo);
                b = this.m2.obtenerNodo(aux[1].getTransicion(simbolo));
            } catch (Exception e) {
                System.out.println("ERROR: " + b + " \n ex" + e);
            }
            System.out.println("b es " + zz);

            if (a.fina && b.fina || !a.fina && !b.fina) {
                columnas[x] = new Comparacion(simbolo, a, b);
                System.out.println(simbolo + "  -->  " + a.nombre + ", " + b.nombre);
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
