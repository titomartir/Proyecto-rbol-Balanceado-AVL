import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que permite exportar un árbol AVL a diferentes formatos
 * (texto plano, DOT para Graphviz, etc.)
 */
public class AVLTreeExporter {
    
    /**
     * Exporta el árbol AVL a un archivo de texto en formato jerárquico
     * @param arbol El árbol AVL a exportar
     * @param nombreArchivo El nombre del archivo donde guardar la exportación
     * @return true si la exportación fue exitosa, false en caso contrario
     */
    public static boolean exportarATexto(AVLTree arbol, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("=== ÁRBOL AVL EXPORTADO ===");
            
            if (arbol.getRaiz() == null) {
                writer.println("Árbol vacío");
                return true;
            }
            
            exportarNodoATexto(arbol.getRaiz(), 0, writer);
            
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar el árbol: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método auxiliar para exportar un nodo y sus hijos a texto
     * @param nodo El nodo a exportar
     * @param nivel El nivel de profundidad del nodo
     * @param writer El PrintWriter para escribir al archivo
     */
    private static void exportarNodoATexto(Node nodo, int nivel, PrintWriter writer) {
        if (nodo == null) {
            return;
        }
        
        // Exportar rama derecha
        exportarNodoATexto(nodo.derecha, nivel + 1, writer);
        
        // Exportar nodo actual
        for (int i = 0; i < nivel; i++) {
            writer.print("    "); // 4 espacios por nivel
        }
        writer.println(nodo.valor);
        
        // Exportar rama izquierda
        exportarNodoATexto(nodo.izquierda, nivel + 1, writer);
    }
    
    /**
     * Exporta el árbol AVL a un archivo DOT para visualizarlo con Graphviz
     * @param arbol El árbol AVL a exportar
     * @param nombreArchivo El nombre del archivo donde guardar la exportación
     * @return true si la exportación fue exitosa, false en caso contrario
     */
    public static boolean exportarADOT(AVLTree arbol, String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.println("digraph AVLTree {");
            writer.println("    node [shape=circle, fontname=\"Arial\", fontsize=12];");
            writer.println("    edge [arrowhead=vee, arrowsize=0.8];");
            
            if (arbol.getRaiz() != null) {
                generarDOT(arbol.getRaiz(), writer);
            } else {
                writer.println("    empty [label=\"Árbol vacío\", shape=plaintext];");
            }
            
            writer.println("}");
            
            return true;
        } catch (IOException e) {
            System.err.println("Error al exportar el árbol a DOT: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Método auxiliar para generar el código DOT de un nodo y sus conexiones
     * @param nodo El nodo actual
     * @param writer El PrintWriter para escribir al archivo
     */
    private static void generarDOT(Node nodo, PrintWriter writer) {
        if (nodo == null) {
            return;
        }
        
        // Generar nodo actual
        writer.println("    node_" + nodo.valor + " [label=\"" + nodo.valor + "\"];");
        
        // Generar conexiones con hijos
        if (nodo.izquierda != null) {
            writer.println("    node_" + nodo.valor + " -> node_" + nodo.izquierda.valor + " [label=\"L\"];");
            generarDOT(nodo.izquierda, writer);
        } else {
            writer.println("    null_left_" + nodo.valor + " [label=\"NULL\", shape=point];");
            writer.println("    node_" + nodo.valor + " -> null_left_" + nodo.valor + " [label=\"L\", style=dotted];");
        }
        
        if (nodo.derecha != null) {
            writer.println("    node_" + nodo.valor + " -> node_" + nodo.derecha.valor + " [label=\"R\"];");
            generarDOT(nodo.derecha, writer);
        } else {
            writer.println("    null_right_" + nodo.valor + " [label=\"NULL\", shape=point];");
            writer.println("    node_" + nodo.valor + " -> null_right_" + nodo.valor + " [label=\"R\", style=dotted];");
        }
    }
}