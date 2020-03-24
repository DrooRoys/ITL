package afdequivalent;

import java.util.HashMap;
import java.util.Map;

public class Node {
    protected String nombre;
    protected boolean inicial, fina;
    protected Node apuntado;
    
    public Node(){
        
    }
    
    public Node(String name, boolean i, boolean f){
        this.nombre = name;
        this.inicial = i;
        this.fina = f;
    }
    
}
