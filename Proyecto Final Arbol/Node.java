/**
 * Clase que representa un nodo en un árbol AVL.
 * Cada nodo contiene un valor entero, referencias a sus hijos izquierdo y derecho,
 * y su altura para mantener el balance del árbol.
 */
public class Node {
    int valor;          // Valor almacenado en el nodo
    Node izquierda;     // Referencia al hijo izquierdo
    Node derecha;       // Referencia al hijo derecho
    int altura;         // Altura del nodo (para el balanceo AVL)
    
    /**
     * Constructor que inicializa un nodo con un valor específico.
     * La altura inicial es 1 (nodo hoja) y no tiene hijos.
     * 
     * @param valor El valor entero a almacenar en el nodo
     */
    public Node(int valor) {
        this.valor = valor;
        this.altura = 1;  // Inicialmente un nodo nuevo es una hoja (altura 1)
        this.izquierda = null;
        this.derecha = null;
    }
}