import java.util.*;

/**
 * Clase auxiliar para imprimir árboles binarios de forma gráfica
 */
public class TreePrinter {
    
    /**
     * Imprime un nodo y su subárbol de forma gráfica
     * @param root El nodo raíz del árbol o subárbol a imprimir
     */
    public static void printNode(Node root) {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }
    
    /**
     * Método interno recursivo para imprimir cada nivel del árbol
     * @param nodes Lista de nodos en el nivel actual
     * @param level Nivel actual
     * @param maxLevel Profundidad máxima del árbol
     */
    private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }
        
        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        
        printWhitespaces(firstSpaces);
        
        List<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.valor);
                newNodes.add(node.izquierda);
                newNodes.add(node.derecha);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }
            
            printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        
        // Imprimir líneas de conexión: /\ 
        for (int i = 1; i <= edgeLines; i++) {
            for (Node node : nodes) {
                printWhitespaces(firstSpaces - i);
                if (node == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }
                
                if (node.izquierda != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }
                
                printWhitespaces(i + i - 1);
                
                if (node.derecha != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }
                
                printWhitespaces(edgeLines + edgeLines - i);
            }
            System.out.println("");
        }
        
        printNodeInternal(newNodes, level + 1, maxLevel);
    }
    
    /**
     * Imprime un número especificado de espacios en blanco
     * @param count Número de espacios a imprimir
     */
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }
    
    /**
     * Calcula la altura máxima (nivel) del árbol
     * @param node Nodo raíz
     * @return Altura máxima del árbol
     */
    private static int maxLevel(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxLevel(node.izquierda), maxLevel(node.derecha)) + 1;
    }
    
    /**
     * Verifica si todos los elementos de una lista son nulos
     * @param list Lista a verificar
     * @return true si todos son nulos, false en caso contrario
     */
    private static <T> boolean isAllElementsNull(List<T> list) {
        for (T object : list) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }
}